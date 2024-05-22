package com.example.quizapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AlertScore(
    score: Int = 0,
    onAlertOpen: () -> Unit = {},
    scoreViewModel: QuickQuizViewModel = viewModel()
) {
    val openAlertDialog = remember { mutableStateOf(true) }
    val uiScoreState by scoreViewModel.uiScoreState.collectAsState()
    val totalCorrectScores = uiScoreState.correctAnswers.size

    when {
        openAlertDialog.value -> {
            AlertDialog(
                text = {
                    Column {
                        Text(text = "You're score is $score out of $totalCorrectScores.")
                        uiScoreState.correctAnswers.forEachIndexed { i, e ->
                            Text(text = "${i + 1}. $e")
                        }
                    }
                },
                onDismissRequest = { },
                confirmButton = { },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openAlertDialog.value = false
                            onAlertOpen()
                        }
                    ) {
                        Text("Close")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlertScorePreview() {
    AlertScore()
}