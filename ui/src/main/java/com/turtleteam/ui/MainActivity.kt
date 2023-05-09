package com.turtleteam.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.controller.Routes
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.TurtleTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigation: NavigationController by inject()
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDark = mutableStateOf(getThemeStateUseCase.execute())

        setContent {
            navController = rememberAnimatedNavController()
            navigation.setNavController(navController)

            DisposableEffect(key1 = Unit, effect = {
               handleIntent(intent)
                onDispose {}
            })

            TurtleAppTheme(isDark.value) {
                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                TurtlesBackground()
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TopBar(
                        onThemeChange = {
                            isDark.value = !isDark.value
                            saveThemeStateUseCase.execute(isDark.value)
                        },
                        navigation.topBarTitle.value
                    )
                    TurtleNavHost(navController)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {handleIntent(it)}
    }

    private fun handleIntent(intent: Intent){
        if (navController.currentBackStackEntry?.destination?.route != Routes.NOTIFICATIONS.name && intent?.action == Routes.NOTIFICATIONS.name) {
            navigation.navigateToNotificationsScreen()
            this.intent.action = null
        }
    }
}