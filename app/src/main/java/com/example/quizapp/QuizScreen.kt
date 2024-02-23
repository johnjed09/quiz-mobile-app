package com.example.quizapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizapp.ui.theme.QuizAppTheme

enum class QuizScreen {
    Start, Flavor, Pickup, Summary, AddQuiz
}

@Composable
fun AddQuestionScreen(onNavigateToQuestions: () -> Unit) {
    var value by remember { mutableStateOf("") }

    QuizAppTheme {
        Column {
            TextField(
                value = value,
                onValueChange = { value = it },
                label = { Text("Input question...") },
                placeholder = { Text(text = "What is the capital of the Philippines?") })
            FilledTonalButton(onClick = { onNavigateToQuestions() }) {
                Text("To Questions...")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddQuestionScreen() {
    var value by remember { mutableStateOf("") }

    QuizAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("Question") }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Input question...") },
                    placeholder = { Text(text = "What is the capital of the Philippines?") })

                FilledTonalButton(onClick = { }) {
                    Text("Add")
                }
            }
        }

    }
}