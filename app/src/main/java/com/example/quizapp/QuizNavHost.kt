package com.example.quizapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun QuizNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = "add_quiz" ) {
        composable("add_quiz") {
            AddQuestionScreen()
        }
        composable("list_quiz") {
            Questions()
        }
    }
}