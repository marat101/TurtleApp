package com.turtleteam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object JetTheme{
    val color : Colors
        @Composable
        get() = LocalColors.current
    val images : Images
        @Composable
        get() = LocalImages.current
}
@Composable
fun TurtleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) Colors.NightColors else Colors.DayColors,
        LocalImages provides if (darkTheme) Images.NightImages else Images.DayImages,
        content = content
    )
}