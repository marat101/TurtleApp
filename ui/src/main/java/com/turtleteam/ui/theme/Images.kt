package com.turtleteam.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import com.turtleteam.ui.R

val LocalImages = staticCompositionLocalOf<Images> { error("images wasnt provided") }

data class Images(
    @DrawableRes val btnNext: Int,
    @DrawableRes val btnSelect: Int,
    @DrawableRes val selectGroup: Int,
    @DrawableRes val selectTeacher: Int,
    @DrawableRes val turtleLeft: Int,
    @DrawableRes val turtleRight: Int,
    @DrawableRes val windowBackground: Int,
    @DrawableRes val topBarIcon: Int,
)

val images = Images(
    btnNext = R.drawable.btnnext,
    btnSelect = R.drawable.btnselect,
    selectGroup = R.drawable.selectgroup,
    selectTeacher = R.drawable.selectteacher,
    turtleLeft = R.drawable.turtleleft,
    turtleRight = R.drawable.turtleright,
    windowBackground = R.drawable.toolbar_gradient,
    topBarIcon = R.drawable.moon
)
val darkImages = Images(
    btnNext = R.drawable.btnnextnight,
    btnSelect = R.drawable.btnselectnight,
    selectGroup = R.drawable.selectgroupnight,
    selectTeacher = R.drawable.selectteachernight,
    turtleLeft = R.drawable.turtleleftnight,
    turtleRight = R.drawable.turtlerightnight,
    windowBackground = R.drawable.toolbar_gradient_night,
    topBarIcon = R.drawable.sun
)

