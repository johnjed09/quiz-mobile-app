package com.example.quizapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

sealed interface QuickQuizUiState {
    object Initial : QuickQuizUiState
    object Loading : QuickQuizUiState
    data class Success(
        val questionSet: List<Question>
    ) : QuickQuizUiState

    data class Error(
        val myError: Exception
    ) : QuickQuizUiState

    data class ScoreUiState(
        val isScoreBoardOpen: Boolean = false,
        val totalScore: Int = 0,
        val correctAnswers: List<String> = emptyList(),
    )
}

class QuickQuizViewModel(private val generativeModel: GenerativeModel) : ViewModel() {
    private val _uiState: MutableStateFlow<QuickQuizUiState> =
        MutableStateFlow(QuickQuizUiState.Initial)
    val uiState: StateFlow<QuickQuizUiState> = _uiState.asStateFlow()

    private val _uiScoreState = MutableStateFlow(QuickQuizUiState.ScoreUiState())
    val uiScoreState: StateFlow<QuickQuizUiState.ScoreUiState> = _uiScoreState.asStateFlow()

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

                val questionSet = Json.decodeFromString<List<Question>>(outputText)

                updateCorrectAnswers(questionSet)
                _uiState.value = QuickQuizUiState.Success(questionSet)
            } catch (e: Exception) {
                Log.e("jed error", "Error: $e")
                _uiState.value = QuickQuizUiState.Error(e)
            }
        }
    }

    private fun updateCorrectAnswers(questionSet: List<Question>) {
        val newList = mutableListOf<String>()

        questionSet.map {
            newList.add(it.correctAnswer)

            updateSelectedAnswers(newList)
        }
    }

    private fun updateSelectedAnswers(newSelectedAnswers: List<String>) {
        _uiScoreState.update { currentState ->
            currentState.copy(
                correctAnswers = newSelectedAnswers
            )
        }
    }

    fun increaseScore() {
        _uiScoreState.update { currentState ->
            currentState.copy(
                totalScore = currentState.totalScore + 1
            )
        }
    }

    fun decreaseScore() {
        _uiScoreState.update { currentState ->
            if (currentState.totalScore == 0) return

            currentState.copy(
                totalScore = currentState.totalScore - 1
            )
        }
    }

    fun switchScoreboardOpen() {
        _uiScoreState.update { currentState ->
            currentState.copy(
                isScoreBoardOpen = !currentState.isScoreBoardOpen
            )
        }
    }
}

