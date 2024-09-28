package com.example.quizapp

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quizapp.databinding.ErrorDialogBinding

@Composable
fun QuickQuizErrorScreen(
    myError: Exception = Exception(),
    addQuestionViewModel: QuickQuizViewModel = viewModel(factory = GeminiViewModelFactory)
) {
    val addQuestionUiState by addQuestionViewModel.uiState.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    fun updateDialog() {
        showDialog = !showDialog
    }

    if (!showDialog) Dialog(onDismissRequest = { showDialog = false }) {
        Card(
            modifier = Modifier.wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
        ) {
            AndroidViewBinding(ErrorDialogBinding::inflate) {
                txtError.text = myError.message

                btnRegenerate.setOnClickListener {
                    updateDialog()

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickQuizErrorPreview() {
    QuickQuizErrorScreen()
}