package com.turtleteam.widget.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.turtleteam.widget.R

object JetTheme{
    val color : Colors
        @Composable
        get() = LocalColors.current
}
val fontFamily = FontFamily(
    Font(R.font.qanelas, weight = FontWeight.Normal)
)
@Composable
fun TurtleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides if (darkTheme) Colors.NightColors else Colors.DayColors,
        content = content
    )
}