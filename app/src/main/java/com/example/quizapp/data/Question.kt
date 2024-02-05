package com.example.quizapp.data

import com.squareup.moshi.Json

data class Question(
    @field:Json(name = "question") val questionText: String,
    @field:Json(name = "choices") val options: List<String>
)