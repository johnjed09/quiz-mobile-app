package com.example.quizapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun QuizNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = "create_quiz") {
        composable("create_quiz") {
            AddQuestionScreenRoute(onNavigateToQuestions = { navController.navigate("list_quiz") })
        }
//        composable("list_quiz") {
//            Questions()
//        }
    }
}