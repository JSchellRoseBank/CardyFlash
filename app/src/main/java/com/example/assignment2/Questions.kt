package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                var score = intent.getIntExtra("score", 0)
                val questions = arrayListOf<String>(
                    "The Great Wall of China was built to protect against invasions from the Mongols.",
                    "Christopher Columbus discovered America in 1776.",
                    "The Roman Empire was ruled by Julius Caesar before it became a republic.",
                    "World War I ended in 1918.",
                    "The Declaration of Independence was signed in 1776.")
                val answers = arrayListOf<Boolean>(true,false,false,true,true)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, top = 80.dp, end = 30.dp)
                ) {



                    // TODO: Remove button
                    Button(onClick = {
                        val next = Intent(this@Questions, MainActivity::class.java)
                        startActivity(next)
                    }) {
                        Text(text = "START")
                    }
                    Text("$score")
                    Text("$questions")
                    Text("$answers")
                }
            }
        }
    }
}
