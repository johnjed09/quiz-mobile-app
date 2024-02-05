package com.example.quizapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp.data.LocalQuestionSet
import com.example.quizapp.data.Question
import com.example.quizapp.data.QuestionSet
import com.example.quizapp.ui.theme.QuizAppTheme

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

//        API CALL
//        GlobalScope.launch {
//            try {
//                val result = RetrofitInstance.retrofitService.getQuizzes()
//
//                Log.d("jed: ", result.toString())
//            } catch (e: IOException) {
//                Log.d("jed error: ", e.toString())
//            }
//        }
    }
}


@Composable
fun Questions() {
    val questionSet = QuestionSet()
    CompositionLocalProvider(LocalQuestionSet provides questionSet) {
        QuestionContainer()
    }
}

@Composable
fun QuestionContainer(questionSet: List<Question> = LocalQuestionSet.current.questionSet) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 38.dp, horizontal = 12.dp)
//            .border(1.dp, Color.Red)
    ) {
        itemsIndexed(questionSet) { i, e ->
            QuestionStatement(
                currentNumber = i + 1, currentQuestion = e.questionText, options = e.options
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
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Row {
            Text(text = "$currentNumber.")
            Text(text = currentQuestion)
        }
        QuestionOptions(options = options)
    }
}

@Composable
fun QuestionOptions(options: List<String>) {
    val (selectedOption, onOptionsSelected) = remember { mutableStateOf(options[0]) }
    options.forEach { option ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .selectable(
                    selected = (option === selectedOption),
                    onClick = { onOptionsSelected(option) },
                    role = Role.RadioButton
                )
                .padding(15.dp, 0.dp, 0.dp, 0.dp)
        ) {
            RadioButton(selected = option == selectedOption, onClick = null)
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