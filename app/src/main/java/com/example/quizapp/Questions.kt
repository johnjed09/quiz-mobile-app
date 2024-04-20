package com.example.quizapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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
            "abc"
        ), Question(
            "Where do Philippines located?", listOf("abc", "def", "gef", "hij"), "abc"
        )
    ),
) {
    Column(modifier = modifier.fillMaxSize()) {
        questionSet.forEachIndexed { i, e ->
            QuestionStatement(
                currentNumber = i + 1,
                currentQuestion = e.question,
                options = e.choices
            )
        }
    }
}

@Composable
fun QuestionStatement(
    currentNumber: Number = 1,
    currentQuestion: String = "Question sentence.",
    options: List<String> = listOf("option 1", "option 2", "option 3", "option 4"),
) {
    Column {
        Row {
            Text(text = "$currentNumber.")
            Text(text = currentQuestion)
        }
        QuestionOptions(options = options)
    }
}

@Composable
fun QuestionOptions(options: List<String>) {
    for (option in options) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(30.dp)
        ) {
            RadioButton(selected = true, onClick = { /*TODO*/ })
            Text(text = option)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    Questions()
}
