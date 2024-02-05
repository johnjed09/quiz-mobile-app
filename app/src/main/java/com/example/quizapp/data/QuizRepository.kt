package com.example.quizapp.data

interface QuizRepository {
    suspend fun getQuizzes(): List<Question>
}

//class NetworkQuizRepository(private val quizApiService: QuizApiService) : QuizRepository {
//    override suspend fun getQuizzes(): List<Question> = quizApiService.getQuizzes()
//}