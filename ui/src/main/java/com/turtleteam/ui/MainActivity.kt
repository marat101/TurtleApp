package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
            val visibility = remember { Animatable(60F) }

            TurtleAppTheme(navigation.isDarkMode.value) {
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                TurtlesBackground()
                Column(modifier = Modifier) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(bottom =visibility.value.dp),
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
                        Modifier.offset(visibility.value.dp),
                        navigation.bottomBarVisible
                    )
                }
            }
            LaunchedEffect(key1 = navigation.bottomBarVisible, block = {
                if (navigation.bottomBarVisible.value)
                    visibility.animateTo(0F)
            })
//            val count = 20
//            val pagerState = rememberPagerState()
//
//            Box(Modifier.fillMaxSize()) {
//                HorizontalPager(count = count, state = pagerState) {
//                    Box(
//                        Modifier
//                            .fillMaxSize()
//                            .background(Color.DarkGray)
//                    ) {
//                        Text(
//                            text = it.toString(),
//                            fontSize = 30.sp
//                        )
//                    }
//                }
//                PagerIndicator(modifier = Modifier.align(Alignment.BottomCenter),count = count, current = pagerState.currentPage)
//            }
        }
    }
}