package com.turtleteam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TurtleAppTheme {
                Scaffold(topBar = { TopAppBar(title = { Text(text = "TurtleApp") }) }, content = {
                    TurtleNavHost()
                })
            }
        }
    }
}