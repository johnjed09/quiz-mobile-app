package com.example.quizapp.data

import com.example.quizapp.Question
import com.example.quizapp.network.QuizApiService

interface QuizRepository {
    suspend fun getQuizzes(): List<Question>
}

//class NetworkQuizRepository(private val quizApiService: QuizApiService) : QuizRepository {
//    override suspend fun getQuizzes(): List<Question> = quizApiService.getQuizzes()
//}