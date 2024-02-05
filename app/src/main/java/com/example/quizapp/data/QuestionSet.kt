package com.example.quizapp.data

import androidx.compose.runtime.compositionLocalOf

data class QuestionSet(
    val questionSet: List<Question> = listOf(
        Question(
            "What is the capital of the Philippines?", listOf("abc", "def", "gef", "hij")
        ), Question(
            "Who is the first president in the Philippines?", listOf("abc", "def", "gef", "hij")
        ), Question(
            "Where do Philippines located?", listOf("abc", "def", "gef", "hij")
        ),
        Question(
            "Who am I?", listOf("abc", "def", "gef", "hij")
        ),
        Question(
            "Who am I?", listOf("abc", "def", "gef", "hij")
        ),
        Question(
            "Who am I?", listOf("abc", "def", "gef", "hij")
        )
    )
)

val LocalQuestionSet = compositionLocalOf { QuestionSet() }
