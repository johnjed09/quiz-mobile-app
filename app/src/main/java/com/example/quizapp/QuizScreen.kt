package com.example.quizapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
internal fun AddQuestionScreenRoute(addQuestionViewModel: QuickQuizViewModel = viewModel(factory = GeminiViewModelFactory)) {
    val addQuestionUiState by addQuestionViewModel.uiState.collectAsState();

    AddQuestionScreen(addQuestionUiState, onGenerateClick = { inputText ->
        addQuestionViewModel.test(inputText)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuestionScreen(
    uiState: QuickQuizUiState = QuickQuizUiState.Loading,
    onGenerateClick: (String) -> Unit = {},
    onNavigateToQuestions: () -> Unit = {}
) {
    var value by remember { mutableStateOf("") }

    QuizAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = { Text("Create Questions") },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Check, contentDescription = "")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding),
                horizontalAlignment = Alignment.End,

                ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Input topic...") },
                )

                FilledTonalButton(
                    onClick = { onGenerateClick(value) },
//                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("Generate")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddQuestionScreenPreview() {
    AddQuestionScreen()
}