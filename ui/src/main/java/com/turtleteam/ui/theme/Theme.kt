package com.turtleteam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object TurtleTheme {
    val color: Colors
        @Composable
        get() = LocalColors.current
    val images: Images
        @Composable
        get() = LocalImages.current
    val shapes: Shapes
        @Composable
        get() = LocalShapes.current
}

val LocalTheme = compositionLocalOf<Boolean> { false }

@Composable
fun TurtleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalTheme provides darkTheme,
        LocalTextStyle provides TextStyle(fontWeight = FontWeight(700),letterSpacing = 0.sp, fontFamily = fontQanelas),
        LocalColors provides if (darkTheme) darkColors else colors,
        LocalImages provides if (darkTheme) darkImages else images,
        LocalShapes provides turtleShapes,
        content = content
    )
}