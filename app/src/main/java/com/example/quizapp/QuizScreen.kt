package com.example.quizapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.ui.theme.QuizAppTheme

@Composable
internal fun AddQuestionScreenRoute(
    onNavigateToQuestions: () -> Unit,
    addQuestionViewModel: QuickQuizViewModel = viewModel(factory = GeminiViewModelFactory),
) {
    val addQuestionUiState by addQuestionViewModel.uiState.collectAsState()

    AddQuestionScreen(addQuestionUiState, onGenerateClick = { inputText ->
        addQuestionViewModel.aiGenerateQuestions(inputText)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuestionScreen(
    uiState: QuickQuizUiState = QuickQuizUiState.Loading,
    scoreViewModel: QuickQuizViewModel = viewModel(),
    onGenerateClick: (String) -> Unit = {},
    onNavigateToQuestions: () -> Unit = {}
) {
    val uiScoreState by scoreViewModel.uiScoreState.collectAsState()

    QuizAppTheme {
        Scaffold(topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ), title = { Text("Create Questions") }, actions = {
                IconButton(onClick = { scoreViewModel.switchScoreboardOpen() }) {
                    Icon(Icons.Filled.Check, contentDescription = "")
                }
            })
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.End,

                ) {
                if (uiScoreState.isScoreBoardOpen) {
                    AlertScore(uiScoreState.totalScore,
                        onAlertOpen = { scoreViewModel.switchScoreboardOpen() })
                }

                var value by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = value,
                    onValueChange = { value = it },
                    label = { Text("Input topic...") },
                )

                FilledTonalButton(
                    onClick = {
                        onGenerateClick(value)
                        onNavigateToQuestions()
                    }, modifier = Modifier.padding(10.dp)
                ) {
                    Text("Generate")
                }

                when (uiState) {
                    QuickQuizUiState.Initial -> {}

                    QuickQuizUiState.Loading -> {}

                    is QuickQuizUiState.Success -> {
                        Questions(questionSet = uiState.questionSet)
                    }
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