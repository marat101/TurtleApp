package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.view.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    private val navigation: NavigationController by inject()

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val topBarTitle = mutableStateOf("TurtleApp")
        val bottomNavViewVisible = mutableStateOf(false)
        val isDarkMode = mutableStateOf(getThemeStateUseCase.execute())

        setContent {

            val pagerState = rememberPagerState()
            val navController = rememberNavController()

            navigation.navController = navController
            navigation.setTopBarTitle(topBarTitle)
            navigation.bottomBarVisible = bottomNavViewVisible

            TurtleAppTheme(isDarkMode.value) {
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                TurtlesBackground()
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TopBar(
                        isDarkMode = isDarkMode,
                        onThemeChange = {
                            saveThemeStateUseCase.execute(it)
                            bottomNavViewVisible.value = !bottomNavViewVisible.value
                        },
                        topBarTitle.value
                    )
                    TurtleNavHost(navController, pagerState)
                    BottomNavigationMenu(pagerState, bottomNavViewVisible)
                }
            }
        }
    }

}