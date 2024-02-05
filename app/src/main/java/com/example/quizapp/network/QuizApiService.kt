package com.example.quizapp.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val retrofit =
    Retrofit.Builder().baseUrl("http://asdf:8080/")
        .addConverterFactory(ScalarsConverterFactory.create()).build()

interface QuizApiService {
    @GET("getQuizzes")
    suspend fun getQuizzes(): String
}

object RetrofitInstance {
    val retrofitService: QuizApiService by lazy {
        retrofit.create(QuizApiService::class.java)
    }
}