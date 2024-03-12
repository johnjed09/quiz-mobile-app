package com.example.quizapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface QuickQuizUiState {
    object Initial : QuickQuizUiState;
    object Loading : QuickQuizUiState;
    data class Success(
        val outputText: String
    ) : QuickQuizUiState
}

class QuickQuizViewModel(private val generativeModel: GenerativeModel) : ViewModel() {
    private val _uiState: MutableStateFlow<QuickQuizUiState> =
        MutableStateFlow(QuickQuizUiState.Initial)
    val uiState: StateFlow<QuickQuizUiState> = _uiState.asStateFlow();

    fun test(inputText: String) {
        _uiState.value = QuickQuizUiState.Loading

        val customPrompt = "Summarize the following text for me: ${inputText}" // Update prompt

        viewModelScope.launch {
            try {
                var outputText = ""
                generativeModel.generateContentStream(customPrompt).collect { response ->
                    outputText += response.text
                    _uiState.value = QuickQuizUiState.Success(outputText)
                }
            } catch (e: Exception) {
                Log.d("jed", "Error") // Uistate error handler
            }
        }

    }

}