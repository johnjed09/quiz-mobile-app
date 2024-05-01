package com.example.quizapp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AlertScore(score: Int = 0, onAlertOpen: () -> Unit = {}) {
    val openAlertDialog = remember { mutableStateOf(true) }

    when {
        openAlertDialog.value -> {
            AlertDialog(
                text = {
                    Text(text = "You're score is ${score}.")
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