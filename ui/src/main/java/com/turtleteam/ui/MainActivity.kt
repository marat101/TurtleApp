package com.turtleteam.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import com.turtleteam.ui.theme_animator.ThemeAnimator
import org.koin.android.ext.android.inject
import ru.turtleteam.theme.TurtleAppTheme


class MainActivity : ComponentActivity() {

    private val navigation: NavigationController by inject()
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()
    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb()
        window.navigationBarColor = Color.Transparent.toArgb()
        super.onCreate(savedInstanceState)
        val isDark = mutableStateOf(getThemeStateUseCase.execute())
        setContent {
            navController = rememberAnimatedNavController()
            navigation.setNavController(navController)
            TurtleAppTheme(isDark.value) {
//                window.setBackgroundDrawableResource(TurtleTheme.images.windowBackground)
                ThemeAnimator(
                    modifier = Modifier.fillMaxSize(),
                    isDark = isDark.value,
                    onThemeChange = {
                        isDark.value = !isDark.value
                        saveThemeStateUseCase.execute(isDark.value)
                    }) {
                    TurtlesBackground()
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        TopBar(navigation.topBarTitle.value)
                        TurtleNavHost(navController)
                    }
                }
            }
        }
    }
}