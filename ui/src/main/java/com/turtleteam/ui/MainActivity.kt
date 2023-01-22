package com.turtleteam.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.lightBrush1

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.Transparent.toArgb()
        window.setBackgroundDrawableResource(R.drawable.toolbar_gradient)
        super.onCreate(savedInstanceState)

        setContent {
            TurtleAppTheme {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = ""
                )
                Column(modifier = Modifier.fillMaxSize()) {
                    TopAppBar(
                        title = { Text(text = "TurtleApp") },
                        modifier = Modifier.background(lightBrush1),
                        contentColor = Color.White,
                        backgroundColor = Color.Transparent,
                        elevation = 0.dp
                    )
                    TurtleNavHost()
                }
            }
        }
    }
}