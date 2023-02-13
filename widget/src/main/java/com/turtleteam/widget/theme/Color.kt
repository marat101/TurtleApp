package com.turtleteam.widget.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple = Color(0xFFBF76C2)

val LocalColors = staticCompositionLocalOf<Colors> { error("colors wasnt provided") }

sealed class Colors(
    val transparentBackground: Color,
    val btnGroupTeacherText: Color,
    val btnDoneText: Color,
    val bottomDialogBackItemColor: Color,
    val titleText: Color,
    val secondText: Color,
    val simpleText: Color,
    val moreScreenIconsTint: Color,
    val bottomNavMenuColors: BottomNavMenuColors,
    val toolbarGradient: Brush,
    val bottomNavBarGradient: Brush,
    val backgroundBrush: Brush,
) {
    object NightColors : Colors(
        transparentBackground = Color(0xBF575756),
        btnGroupTeacherText = Purple,
        btnDoneText = Purple,
        bottomDialogBackItemColor = Color(0xBF575756),
        titleText = Color(0xFF8D91D1),
        secondText = Purple,
        simpleText = Color.White,
        moreScreenIconsTint = Color(0xFF8D91D1),
        bottomNavMenuColors = BottomNavMenuColors.NightColors,
        toolbarGradient = Brush.horizontalGradient(
            listOf(
                Color(0xFF1F3242),
                Color(0xFF033E4A),
            )
        ),
        bottomNavBarGradient = Brush.linearGradient(
            listOf(Color(0xFF033E4A), Color(0xFF033E4A))
        ),
        backgroundBrush = Brush.horizontalGradient(listOf(Color(0xFF3C3030), Color(0xFF3C3030)))
    )

    object DayColors : Colors(
        transparentBackground = Color(0xBFFFFFFF),
        btnGroupTeacherText = Color.Black,
        btnDoneText = Color.White,
        bottomDialogBackItemColor = Color.White,
        titleText = Color(0xFF96D162),
        secondText = Color(0xFF15956F),
        simpleText = Color.Gray,
        moreScreenIconsTint = Color.Black,
        bottomNavMenuColors = BottomNavMenuColors.DayColors,
        toolbarGradient = Brush.horizontalGradient(listOf(Color(0xFF15956F), Color(0xFF96D162))),
        bottomNavBarGradient = Brush.linearGradient(
            listOf(Color(0xFF86C8A7), Color(0xFFB3E3AE))
        ),
        backgroundBrush = Brush.horizontalGradient(
            listOf(Color(0xFFB5E7AB), Color(0xFFFCFDD7)),
        )
    )
}

sealed class BottomNavMenuColors(
    private val isCheckedTrue: Color,
    private val isCheckedFalse: Color,
) {
    fun getColor(isChecked:Boolean):Color = if (isChecked) isCheckedTrue else isCheckedFalse
    object NightColors : BottomNavMenuColors(
        isCheckedTrue = Color(0xFF8D91D1), isCheckedFalse = Color(0xFF57596D)
    )

    object DayColors : BottomNavMenuColors(
        isCheckedTrue = Color(0xFFFFFFFF), isCheckedFalse = Color(0xFF575756)
    )
}