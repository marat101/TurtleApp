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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.turtleteam.domain.usecases.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.navigation.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.TopBar
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.JetTheme
import com.turtleteam.ui.theme.TurtleAppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    @OptIn(ExperimentalPagerApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember { mutableStateOf(getThemeStateUseCase.execute()) }
            val pagerState = rememberPagerState()
            val navController = rememberNavController()
            TurtleAppTheme(isDarkMode.value) {
                window.setBackgroundDrawableResource(JetTheme.images.windowBackground)
                Scaffold(
                    topBar = { TopBar(isDarkMode = isDarkMode, saveThemeStateUseCase) },
                    bottomBar = {
                        BottomNavigationMenu(
                            pagerState,
                            navController.currentBackStackEntryAsState()
                        )
                    }
                ) {
                    Box(
                        Modifier.fillMaxWidth().background(JetTheme.color.backgroundBrush)
                    ) {
                        TurtlesBackground()
                        TurtleNavHost(navController, pagerState)
                    }
                }
            }
        }
    }
}