package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TurtleAppTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    TopAppBar(title = {Text(text = "TurtleApp")})
                    TurtleNavHost()
                }
            }
        }
    }
}