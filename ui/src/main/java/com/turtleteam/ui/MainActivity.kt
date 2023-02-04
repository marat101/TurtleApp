package com.turtleteam.ui

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
import com.turtleteam.ui.screens.navigation.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.TopBar
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.JetTheme
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.utils.TurtlesBackground
import com.turtleteam.ui.utils.switch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vModel by viewModel<ActivityViewModel>()

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkMode = remember { mutableStateOf(vModel.getThemeState()) }
            val pagerState = rememberPagerState()
            val navController = rememberNavController()
            val toolBarText = remember { mutableStateOf(this.getString(R.string.turtle_team)) }
            TurtleAppTheme(isDarkMode.value) {
                window.setBackgroundDrawableResource(JetTheme.images.windowBackground)
                Scaffold(
                    topBar = {
                        TopBar(
                            text = toolBarText.value,
                            switchTheme = { vModel.saveThemeState(isDarkMode.switch()) }
                        )
                    },
                    bottomBar = {
                        BottomNavigationMenu(
                            pagerState = pagerState,
                            backStack = navController.currentBackStackEntryAsState()
                        )
                    },
                    content = {
                        Box(
                            Modifier.padding(it).background(JetTheme.color.backgroundBrush)
                        ) {
                            TurtlesBackground()
                            TurtleNavHost(navController, pagerState, toolBarText)
                        }
                    }
                )
            }
        }
    }
}