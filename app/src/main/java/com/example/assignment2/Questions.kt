package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.assignment2.MainActivity
import com.example.assignment2.ui.theme.Assignment2Theme
import org.w3c.dom.Text

class Questions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                var scoreCounter by remember { mutableIntStateOf(0) }
                var questionNumber by remember { mutableIntStateOf(0) }
                var answerResponse by remember { mutableStateOf(String()) }
                var answerToQuestion by remember { mutableIntStateOf(0)}
                var reviewView by remember { mutableStateOf(false) }
                val questions = listOf<String>(
                    "The Great Wall of China was built to protect against invasions from the Mongols.",
                    "Christopher Columbus discovered America in 1776.",
                    "The Roman Empire was ruled by Julius Caesar before it became a republic.",
                    "World War I ended in 1918.",
                    "The Declaration of Independence was signed in 1776.")
                val answers = listOf<Boolean>(true,false,false,true,true)
                var userAnswer = false

                if( questionNumber < 5) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 30.dp, top = 80.dp, end = 30.dp)
                    ) {
                        Text(text = questions[questionNumber])

                        Row {
                            Button(onClick = {
                                userAnswer = true
                                if (answers[answerToQuestion] == userAnswer) {
                                    scoreCounter++
                                    answerResponse = "Correct!"
                                } else  {
                                    answerResponse = "Incorrect!"
                                }
                            }) {
                                Text(text = "True")
                            }

                            Button(onClick = {
                                userAnswer = false
                                if (answers[answerToQuestion] == userAnswer) {
                                    scoreCounter++
                                    answerResponse = "Correct!"
                                } else  {
                                    answerResponse = "Incorrect!"
                                }
                            }) {
                                Text(text = "False")
                            }

                        }
                        Row {
                            Text(text = answerResponse)
                            Text(text = "Score: $scoreCounter")
                        }
                        Button(onClick = {
                            questionNumber++
                            answerToQuestion++
                            answerResponse = ""
                        }) {
                            Text(text = "Next")
                        }

                        // TODO: Remove button
                        Button(onClick = {
                            val next = Intent(this@Questions, MainActivity::class.java)
                            startActivity(next)
                        }) {
                            Text(text = "Back")
                        }
                    }
                } else {
                    Column {
                        Text(text = "Score: $scoreCounter")
                        if(scoreCounter >= 3) {
                            Text(text = "Great job!")
                        } else {
                            Text(text = "Keep practicing!")
                        }
                        Button(
                            onClick = {
                                reviewView = true
                            }
                        ) {
                            Text(text = "Review")
                        }

                        Button(onClick = {
                            val next = Intent(this@Questions, MainActivity::class.java);
                            startActivity(next)
                            scoreCounter = 0
                        }) {
                            Text(text = "Exit")
                        }

                        Row {
                            if(reviewView) {
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
                Column {
                    Text(text = "Q: ${questions[i]}", fontWeight = FontWeight.Bold)
                    Text(text = "A: ${answers[i]}", modifier = Modifier.padding(bottom = 5.dp))
                }
            }
        }
    }
}