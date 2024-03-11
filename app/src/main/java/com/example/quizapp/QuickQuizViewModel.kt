package com.example.quizapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class QuickQuizUiState {
    Initial,
    Loading
}

class QuickQuizViewModel(private val generativeModel: GenerativeModel) : ViewModel() {
    private val _uiState: MutableStateFlow<QuickQuizUiState> =
        MutableStateFlow(QuickQuizUiState.Initial)
    val uiState: StateFlow<QuickQuizUiState> = _uiState.asStateFlow();

    fun test(inputText: String) {
        viewModelScope.launch {
            try {
                generativeModel.generateContentStream("Summarize the following text for me: ${inputText}").collect { response ->
                    Log.d("jed", "Test ${response.text}")

                }
            } catch (e: Exception) {
                Log.d("jed", "Error")
            }
        }

    }

}