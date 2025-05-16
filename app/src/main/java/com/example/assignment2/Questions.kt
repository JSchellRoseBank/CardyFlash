package com.example.assignment2

import android.R
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.ui.theme.Assignment2Theme

class Questions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                var scoreCounter by remember { mutableIntStateOf(0) }
                var questionNumber by remember { mutableIntStateOf(0) }
                var answerResponse by remember { mutableStateOf(String()) }
                var answerToQuestion by remember { mutableIntStateOf(0) }
                var reviewView by remember { mutableStateOf(false) }
                var userAnswer by remember { mutableStateOf(false) }
                var hasUserAnswered by remember { mutableStateOf(false) }
                val questions = listOf<String>(
                    "The Great Wall of China was built to protect against invasions from the Mongols.",
                    "Christopher Columbus discovered America in 1776.",
                    "The Roman Empire was ruled by Julius Caesar before it became a republic.",
                    "World War I ended in 1918.",
                    "The Declaration of Independence was signed in 1776."
                )
                val answers = listOf<Boolean>(true, false, false, true, true)

                if (questionNumber < 1) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, top = 100.dp, end = 30.dp)
                    ) {
                        Text(
                            text = questions[questionNumber],
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .background(Color.Black)
                                .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                                .height(200.dp)
                                .fillMaxWidth(1f),
                            color = Color.White
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                        ) {
                            Column {
                                Text(text = "Score: $scoreCounter")
                            }
                            Spacer(modifier = Modifier.width(200.dp))
                            Column {
                                Text(text = answerResponse)
                            }
                        }

                        Row(
                            modifier = Modifier.padding(top = 20.dp)
                        ) {

                            Row(
                                modifier = Modifier.height(75.dp)
                            ) {

                                if (hasUserAnswered == false) {
                                    Button(
                                        modifier = Modifier
                                            .width(125.dp)
                                            .height(50.dp),
                                        onClick = {
                                            userAnswer = true
                                            hasUserAnswered = true

                                            if (hasUserAnswered) {
                                                if (answers[answerToQuestion] == userAnswer) {
                                                    scoreCounter++
                                                    answerResponse = "Correct!"
                                                } else {
                                                    answerResponse = "Incorrect!"
                                                }
                                            }

                                        }) {
                                        Text(text = "True")
                                    }

                                    Spacer(modifier = Modifier.width(80.dp))

                                    Button(
                                        modifier = Modifier
                                            .width(125.dp)
                                            .height(50.dp),
                                        onClick = {
                                            userAnswer = false
                                            hasUserAnswered = true

                                            if (hasUserAnswered) {
                                                if (answers[answerToQuestion] == userAnswer) {
                                                    scoreCounter++
                                                    answerResponse = "Correct!"
                                                } else {
                                                    answerResponse = "Incorrect!"
                                                }
                                            }
                                        }
                                    ) {
                                        Text(text = "False")
                                    }
                                } else {
                                    Text(text = "Please proceed to the next question")
                                }
                            }

                        }

                        Button(
                            modifier = Modifier
                                .width(125.dp)
                                .height(50.dp),
                            onClick = {
                                questionNumber++
                                answerToQuestion++
                                answerResponse = ""
                                hasUserAnswered = false
                            }) {
                            Text(
                                text = "Next"
                            )
                        }
                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, top = 100.dp, end = 30.dp)
                    ) {
                        Text(
                            text = "Score: $scoreCounter", fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        if (scoreCounter >= 3) {
                            Text(
                                text = "Great job!", fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Text(
                                text = "Keep practicing!", fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Button(
                            modifier = Modifier
                                .padding(top = 25.dp)
                                .height(50.dp),
                            onClick = {
                                reviewView = true
                            }
                        ) {
                            Text(text = "Review Questions And Answers")
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            modifier = Modifier
                                .width(125.dp)
                                .height(50.dp),
                            onClick = { /* TODO: Terminate app */
                            val next = Intent(this@Questions, MainActivity::class.java)
                            startActivity(next)
                            scoreCounter = 0
                        }) {
                            Text(
                                text = "Exit"
                            )
                        }

                        Row {
                            if (reviewView) {
                                Column {
                                    ReviewQA(questions, answers)
                                }
                            } else {
                                Text(text = "")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewQA(questions: List<String>, answers: List<Boolean>) {
    Column(modifier = Modifier.padding(5.dp)) {
        if (questions.isEmpty() || answers.isEmpty()) {
            Text("No questions or answers to display.")
        } else {
            for (i in questions.indices) {
                Column(modifier = Modifier.padding(top = 15.dp)) {
                    Text(text = "Q: ${questions[i]}", fontWeight = FontWeight.Bold)
                    Text(text = "A: ${answers[i]}", modifier = Modifier.padding(bottom = 5.dp), color = Color.Green)
                }
            }
        }
    }
}