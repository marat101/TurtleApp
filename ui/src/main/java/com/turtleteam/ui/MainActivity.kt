package com.turtleteam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.turtleteam.ui.screens.navigation.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.TopBar
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.JetTheme
import com.turtleteam.ui.theme.TurtleAppTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember{ mutableStateOf(false) } // todo save current mode
            val navController = rememberNavController()
            window.setBackgroundDrawableResource(if (isDarkMode.value) R.drawable.toolbar_gradient_night else R.drawable.toolbar_gradient)
            TurtleAppTheme(isDarkMode.value) {
                Column(modifier = Modifier.fillMaxSize()) {
                    TopBar(isDarkMode = isDarkMode)
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(JetTheme.color.backgroundBrush)
                    ) {
                        TurtlesBackground()
                        TurtleNavHost(navController)
                    }
                    BottomNavigationMenu(navController)
                }
            }
        }
    }
}