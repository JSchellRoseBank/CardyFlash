package com.example.assignment2

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
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
                val context = LocalContext.current

                var scoreCounter by remember { mutableIntStateOf(0) }
                var questionNumber by remember { mutableIntStateOf(0) }
                var answerResponse by remember { mutableStateOf("") }
                var hasUserAnswered by remember { mutableStateOf(false) }
                var reviewView by remember { mutableStateOf(false) }

                val questions = listOf(
                    "The Great Wall of China was built to protect against invasions from the Mongols.",
                    "Christopher Columbus discovered America in 1776.",
                    "The Roman Empire was ruled by Julius Caesar before it became a republic.",
                    "World War I ended in 1918.",
                    "The Declaration of Independence was signed in 1776."
                )

                val answers = listOf(true, false, false, true, true)

                if (questionNumber < questions.size) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp, 100.dp, 30.dp, 0.dp)
                    ) {
                        Text(
                            text = questions[questionNumber],
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .background(Color.Black)
                                .padding(20.dp)
                                .height(200.dp)
                                .fillMaxWidth(),
                            color = Color.White
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Score: $scoreCounter")
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = answerResponse)
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        if (!hasUserAnswered) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(80.dp),
                                modifier = Modifier.height(75.dp)
                            ) {
                                Button(
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                                    modifier = Modifier
                                        .width(125.dp)
                                        .height(50.dp),
                                    onClick = {
                                        hasUserAnswered = true
                                        if (answers[questionNumber]) {
                                            scoreCounter++
                                            answerResponse = "Correct!"
                                        } else {
                                            answerResponse = "Incorrect!"
                                        }
                                    }
                                ) {
                                    Text(text = "True", color = Color.Black)
                                }

                                Button(
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                                    modifier = Modifier
                                        .width(125.dp)
                                        .height(50.dp),
                                    onClick = {
                                        hasUserAnswered = true
                                        if (!answers[questionNumber]) {
                                            scoreCounter++
                                            answerResponse = "Correct!"
                                        } else {
                                            answerResponse = "Incorrect!"
                                        }
                                    }
                                ) {
                                    Text(text = "False", color = Color.Black)
                                }
                            }
                        } else {
                            Text(text = "Please proceed to the next question")
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                            modifier = Modifier
                                .width(125.dp)
                                .height(50.dp),
                            onClick = {
                                questionNumber++
                                answerResponse = ""
                                hasUserAnswered = false
                            }
                        ) {
                            Text(text = "Next", color = Color.Black)
                        }
                    }
                } else {
                    // End of quiz view
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp, 100.dp, 30.dp, 0.dp)
                    ) {
                        Text(
                            text = "Score: $scoreCounter",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = if (scoreCounter >= 3) "Great job!" else "Keep practicing!",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                            modifier = Modifier
                                .padding(top = 25.dp)
                                .height(50.dp),
                            onClick = { reviewView = true }
                        ) {
                            Text(text = "Review Questions And Answers", color = Color.Black)
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                            modifier = Modifier
                                .width(125.dp)
                                .height(50.dp),
                            onClick = {
                                (context as? Activity)?.finishAffinity()
                            }
                        ) {
                            Text(text = "Exit", color = Color.Black)
                        }

                        if (reviewView) {
                            ReviewQA(questions, answers)
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
        questions.zip(answers).forEachIndexed { index, (question, answer) ->
            Column(modifier = Modifier.padding(top = 15.dp)) {
                Text(text = "Q: $question", fontWeight = FontWeight.Bold)
                Text(text = "A: ${if (answer) "True" else "False"}", modifier = Modifier.padding(bottom = 5.dp), color = Color.Green)
            }
        }
    }
}
