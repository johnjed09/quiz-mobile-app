package com.example.quizapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Questions(
    modifier: Modifier = Modifier,
    onNavigateToAddQuiz: () -> Unit = {},
    questionSet: List<Question> = listOf(
        Question(
            "What is the capital of the Philippines?", listOf("abc", "def", "gef", "hij"), "abc"
        ), Question(
            "Who is the first president in the Philippines?",
            listOf("abc", "def", "gef", "hij"),
            "def"
        ), Question(
            "Where do Philippines located?", listOf("abc", "def", "gef", "hij"), "hij"
        )
    ),
) {
    Column(modifier = modifier.fillMaxSize()) {
        questionSet.forEachIndexed { i, e ->
            QuestionStatement(
                index = i,
                currentNumber = i + 1,
                currentQuestion = e.question,
                options = e.choices,
            )
        }
    }
}

@Composable
fun QuestionStatement(
    index: Int,
    currentNumber: Number = 1,
    currentQuestion: String = "Question sentence.",
    options: List<String> = listOf("option 1", "option 2", "option 3", "option 4"),
) {
    Column {
        Row {
            Text(text = "$currentNumber.")
            Text(text = currentQuestion)
        }
        QuestionOptions(index = index, options = options)
    }
}

@Composable
fun QuestionOptions(
    index: Int, options: List<String>, viewModel: QuickQuizViewModel = viewModel()
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    val uiScoreState by viewModel.uiScoreState.collectAsState()
    var isOptionCorrect by remember { mutableStateOf(false) }

    LaunchedEffect(isOptionCorrect) {
        if (selectedOption.isBlank()) {
            return@LaunchedEffect
        }

        if (isOptionCorrect) {
            viewModel.increaseScore()
        } else {
            viewModel.decreaseScore()
        }
    }

    for (option in options) {
        key(index) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(30.dp)
                    .selectableGroup()
            ) {
                RadioButton(selected = (option == selectedOption), onClick = {
                    onOptionSelected(option)

                    Log.d("jed select", "${uiScoreState.correctAnswers[index]} || ${option}")
                    isOptionCorrect = uiScoreState.correctAnswers[index] == option
                })
                Text(text = option)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    Questions()
}
