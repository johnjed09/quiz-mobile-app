package com.example.quizapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

sealed interface QuickQuizUiState {
    object Initial : QuickQuizUiState
    object Loading : QuickQuizUiState
    data class Success(
        val questionSet: List<Question>
    ) : QuickQuizUiState
}

class QuickQuizViewModel(private val generativeModel: GenerativeModel) : ViewModel() {
    private val _uiState: MutableStateFlow<QuickQuizUiState> =
        MutableStateFlow(QuickQuizUiState.Initial)
    val uiState: StateFlow<QuickQuizUiState> = _uiState.asStateFlow()

    fun aiGenerateQuestions(inputText: String) {
        _uiState.value = QuickQuizUiState.Loading

        val jsonResponseAiPrompt =
            "Output as JSON array, mapping on these properties: question, choices(question choices or options), correctAnswer(question correct choices or options)."
        val customPrompt =
            "Create multiple choice questions based on this topic: ${inputText}. ${jsonResponseAiPrompt}"

        viewModelScope.launch {
            try {
                var outputText = ""
                val response = generativeModel.generateContent(customPrompt)

                outputText += response.text?.trim('`')?.removePrefix("json")
//                Log.d("jed", outputText)

                val questionSet = Json.decodeFromString<List<Question>>(outputText)

//                questionSet.map { Log.d("jed", it.question) }

                _uiState.value = QuickQuizUiState.Success(questionSet)
            } catch (e: Exception) {
                Log.d("jed error", "Error: $e")
            }
        }
    }
}

