package com.turtleteam.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val green = Color(0xFF648B4A)
val transparentGreen = Color(0x40648B4A)

// Светлая Тёма
val darkGreen = Color(0xFF15956F)
val lightGreen = Color(0xFF96D162)
val transparentWhite = Color(255, 255, 255, 0x40)
val lightBrush1 = Brush.horizontalGradient(listOf(darkGreen ,lightGreen))
val lightBackgroundBrush = Brush.linearGradient(
    listOf(Color(0xFF86C8A7), Color(0xFFB3E3AE)),
    start = Offset(0f, Float.POSITIVE_INFINITY),
    end = Offset(Float.POSITIVE_INFINITY, 0f)
)


// Темная Тёма
val darkColor1 = Color(0xFFBF76C2)
val darkColor2 = Color(0xFF033E4A)
val darkColor3 = Color(0xFF8D91D1)