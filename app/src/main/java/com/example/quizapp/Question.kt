package com.example.quizapp

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val question: String,
    val choices: List<String>,
    val correctAnswer: String
)