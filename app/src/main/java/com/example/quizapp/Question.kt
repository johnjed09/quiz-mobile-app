package com.example.quizapp

import com.squareup.moshi.Json

data class Question(
    @field:Json(name = "question") val questionText: String,
    @field:Json(name = "choices") val options: List<String>
)