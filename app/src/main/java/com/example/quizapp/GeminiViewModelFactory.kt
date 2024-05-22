package com.example.quizapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig

val GeminiViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val config = generationConfig { temperature = 0.7f }

        return QuickQuizViewModel(
            generativeModel = GenerativeModel(
                modelName = "gemini-1.0-pro",
                apiKey = BuildConfig.apiKey,
                generationConfig = config
            )
        ) as T
    }
}