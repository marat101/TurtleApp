package com.turtleteam.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.compositionLocalOf
import com.turtleteam.ui.R

val LocalImages = compositionLocalOf<Images> { error("images wasnt provided") }

data class Images(
    @DrawableRes val btnNext: Int,
    @DrawableRes val selectGroup: Int,
    @DrawableRes val selectTeacher: Int,
    @DrawableRes val windowBackground: Int,
    @DrawableRes val topBarIcon: Int,
)

val images = Images(
    btnNext = R.drawable.btnnext,
    selectGroup = R.drawable.selectgroup,
    selectTeacher = R.drawable.selectteacher,
    windowBackground = R.drawable.toolbar_gradient,
    topBarIcon = R.drawable.moon
)
val darkImages = Images(
    btnNext = R.drawable.btnnextnight,
    selectGroup = R.drawable.selectgroupnight,
    selectTeacher = R.drawable.selectteachernight,
    windowBackground = R.drawable.toolbar_gradient_night,
    topBarIcon = R.drawable.sun
)

