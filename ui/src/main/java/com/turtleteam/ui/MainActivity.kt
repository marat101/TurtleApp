package com.turtleteam.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.turtleteam.domain.usecases.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases.usersettings.SaveThemeStateUseCase
import com.turtleteam.ui.screens.navigation.BottomNavigationMenu
import com.turtleteam.ui.screens.navigation.TopBar
import com.turtleteam.ui.screens.navigation.TurtleNavHost
import com.turtleteam.ui.theme.JetTheme
import com.turtleteam.ui.theme.TurtleAppTheme
import com.turtleteam.ui.utils.TurtlesBackground
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    @OptIn(ExperimentalPagerApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val isDarkMode = remember { mutableStateOf(getThemeStateUseCase.execute()) }
            val pagerState = rememberPagerState()
            val navController = rememberNavController()
            TurtleAppTheme(isDarkMode.value) {
                window.setBackgroundDrawableResource(JetTheme.images.windowBackground)
                Scaffold(
                    topBar = { TopBar(isDarkMode = isDarkMode, saveThemeStateUseCase) },
                    bottomBar = {
                        BottomNavigationMenu(
                            pagerState,
                            navController.currentBackStackEntryAsState()
                        )
                    }
                ) {
                    Box(
                        Modifier.fillMaxWidth().background(JetTheme.color.backgroundBrush)
                    ) {
                        TurtlesBackground()
                        TurtleNavHost(navController, pagerState)
                    }
                }
            }

//            val indication1: CustomIndication = CustomIndication(
//                pressColor = Color.Cyan,
//                cornerRadius = CornerRadius(30f, 30f),
//                alpha = .7f
//            )
//
//            val indication2: CustomIndication = CustomIndication(
//                pressColor = Color.Red,
//                cornerRadius = CornerRadius(16f, 16f),
//                alpha = .5f
//            )
//
//            val indication3: CustomIndication = CustomIndication(
//                pressColor = Color(0xffFFEB3B),
//                alpha = .4f,
//                drawRoundedShape = false,
//            )
//
//            Box(
//                modifier = Modifier.fillMaxSize().background(Color.White).clickable(
//                    interactionSource = MutableInteractionSource(),
//                    indication = indication2,
//                    onClick = {}
//                ),
//                contentAlignment = Alignment.Center
//            ) {
//            }

        }
    }
}




private class CustomIndication(
    val pressColor: Color = Color.Red,
    val cornerRadius: CornerRadius = CornerRadius(16f, 16f),
    val alpha: Float = 0.5f,
    val drawRoundedShape: Boolean = true
) : Indication {

    private inner class DefaultIndicationInstance(
        private val isPressed: State<Boolean>,
    ) : IndicationInstance {

        override fun ContentDrawScope.drawIndication() {

            drawContent()
            when {
                isPressed.value -> {
                    if (drawRoundedShape) {
                        drawRoundRect(
                            cornerRadius = cornerRadius,
                            color = pressColor.copy(
                                alpha = alpha
                            ), size = size
                        )
                    }
                }
            }
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        val isPressed = interactionSource.collectIsPressedAsState()
        return remember(interactionSource) {
            DefaultIndicationInstance(isPressed)
        }
    }
}

