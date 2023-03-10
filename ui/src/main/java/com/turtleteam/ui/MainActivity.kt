package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.view.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.utils.BackPress
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigation: NavigationController by inject()
    private val onBack : BackPress by inject()

    //TODO VIEWMODEL

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, onBack.callBack)

        setContent {

            val navController = rememberNavController()

            navigation.setNavController(navController)

            TurtleAppTheme(navigation.isDarkMode.value) {
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                TurtlesBackground()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TopBar(
                        isDarkMode = navigation.isDarkMode,
                        onThemeChange = {
                            navigation.setTheme(it)
                        },
                        navigation.topBarTitle.value
                    )
                    TurtleNavHost(navController, navigation.pagerState)
                    BottomNavigationMenu(navigation.pagerState, navigation.bottomBarVisible)
                }
            }
        }
    }
}