package com.turtleteam.ui

import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.judemanutd.autostarter.AutoStartPermissionHelper
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.common.components.TopBar
import com.turtleteam.ui.screens.common.views.TurtlesBackground
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.view.TurtleNavHost
import com.turtleteam.ui.theme.LocalColors
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.theme.TurtleTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigation: NavigationController by inject()
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDark = mutableStateOf(getThemeStateUseCase.execute())

        setContent {

            val navController = rememberNavController()
            navigation.setNavController(navController)

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
                Text(text = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID),
                color = LocalColors.current.btnDoneText)
            }
        }
    }
}