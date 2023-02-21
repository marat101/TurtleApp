package com.turtleteam.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.navigation.view.BottomNavigationMenu
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.screen_home.HomeScreen
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.utils.views.TurtlesBackground
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    private val navigation: NavigationController by inject()

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        val topBarTitle = mutableStateOf("TurtleApp")

        super.onCreate(savedInstanceState)
        setContent {

            val isDarkMode = remember { mutableStateOf(getThemeStateUseCase.execute()) }
            val pagerState = rememberPagerState()
            val navController = rememberNavController()
            navigation.navController = navController
            TurtleAppTheme(isDarkMode.value) {
                TurtlesBackground()
                Scaffold(backgroundColor = Color.Transparent,topBar = {
                    TopBar(
                        isDarkMode = isDarkMode,
                        onThemeChange = { saveThemeStateUseCase.execute(it) },
                        topBarTitle.value
                    )
                }, bottomBar = {
                    BottomNavigationMenu(
                        pagerState,
//                        navController.currentBackStackEntryAsState()
                    )
                }, content = { padding ->
                    HomeScreen(
                        pagerState = pagerState,
                        modifier = Modifier.padding(
                            bottom = padding.calculateBottomPadding(),
                            top = padding.calculateTopPadding()
                        )
                    )
                })
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
            }
        }
    }
}