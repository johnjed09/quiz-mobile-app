package com.example.quizapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAppTheme

enum class QuizScreen {
    Start, Flavor, Pickup, Summary, AddQuiz
}

//@Preview(showBackground = true)
@Composable
fun AddQuestionScreen(onNavigateToQuestions: () -> Unit) {
    var value by remember { mutableStateOf("") }

    QuizAppTheme {
        Column {
            TextField(value = value, onValueChange = { value = it }, label = { Text("Input question...") }, placeholder = { Text(text = "What is the capital of the Philippines?") })
            FilledTonalButton(onClick = { onNavigateToQuestions() }) {
                Text("To Questions...")
            }
        }

    }
}