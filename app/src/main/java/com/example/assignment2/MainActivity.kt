package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment2Theme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 30.dp, top = 100.dp, end = 30.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = "Welcome",
                            fontSize = 70.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                    Text(
                        modifier = Modifier.padding(top = 25.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light,
                        text = "Our aim is to provide you the opportunity to learn about " +
                                "certain historical events that happened throughout history."
                    )

                    Button(
                        modifier = Modifier
                            .padding(top = 80.dp).width(200.dp).height(50.dp),
                            onClick = {
                            val next = Intent(this@MainActivity, Questions::class.java);
                            startActivity(next)
                    }) {
                        Text(text = "START")
                    }
                }
            }
        }
    }
}
