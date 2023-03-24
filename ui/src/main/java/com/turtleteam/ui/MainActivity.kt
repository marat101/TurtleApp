package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
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

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            navigation.setNavController(navController)
            val visibility = remember { Animatable(0F) }

            TurtleAppTheme(navigation.isDarkMode.value) {
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                TurtlesBackground()
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = (60F - visibility.value).dp),
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
                    }
                    BottomNavigationMenu(
                        navigation.pagerState,
                        Modifier
                            .align(Alignment.BottomCenter)
                            .height(60.dp)
                            .offset(y = visibility.value.dp)
                    )
                }
            }
            LaunchedEffect(key1 = navigation.bottomBarVisible.value, block = {
                if (navigation.bottomBarVisible.value)
                    visibility.animateTo(
                        60F,
                        animationSpec = tween(
                            durationMillis = 150,
                            easing = LinearEasing
                        )
                    )
                else
                    visibility.animateTo(
                        0F,
                        animationSpec = tween(
                            durationMillis = 150,
                            easing = LinearEasing
                        )
                    )
            })
        }
    }
}