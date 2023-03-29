package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.rememberNavController
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.view.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.TurtleTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigation: NavigationController by inject()

    //TODO VIEWMODEL

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            navigation.setNavController(navController)

            TurtleAppTheme(navigation.isDarkMode.value) {
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                TurtlesBackground()
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
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
                    AnimatedVisibility(
                        visible = !navigation.bottomBarVisible.value,
                        enter = slideIn(
                            initialOffset = { fullSize -> IntOffset(0, fullSize.height) },
                            animationSpec = tween(
                                durationMillis = 150,
                                easing = LinearEasing
                            )
                        ),
                        exit = slideOut(
                            targetOffset = { fullSize -> IntOffset(0, fullSize.height) },
                            animationSpec = tween(
                                durationMillis = 150,
                                easing = LinearEasing
                            )
                        )
                    ) {
                        BottomNavigationMenu(
                            navigation.pagerState
                        )
                    }
                }
            }
        }
    }
}