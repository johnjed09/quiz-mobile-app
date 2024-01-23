package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.network.RetrofitInstance
import com.example.quizapp.ui.theme.QuizAppTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Questions()
                }
            }
        }

        GlobalScope.launch {
            try {
                val result = RetrofitInstance.retrofitService.getQuizzes()

                Log.d("jed: ", result.toString())
            } catch(e: IOException) {
                Log.d("jed error: ", e.toString())
            }
        }
    }
}

@Composable
fun Questions() {
    val questionSet: List<Question> = listOf(
        Question(
            "What is the capital of the Philippines?", listOf("abc", "def", "gef", "hij")
        ), Question(
            "Who is the first president in the Philippines?", listOf("abc", "def", "gef", "hij")
        ), Question(
            "Where do Philippines located?", listOf("abc", "def", "gef", "hij")
        )
    )

    Column() {
        questionSet.forEachIndexed { i, e ->
            QuestionStatement(
                currentNumber = i + 1,
                currentQuestion = e.questionText,
                options = e.options
            )
        }
    }
}

@Composable
fun QuestionStatement(
    currentNumber: Number = 1,
    currentQuestion: String = "What is the capital of the Philippines?",
    options: List<String> = listOf("Manila", "Cebu City", "Maguinadanao", "Palawan"),
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
fun DefaultPreview() {
    QuizAppTheme {
        Questions()
    }
}