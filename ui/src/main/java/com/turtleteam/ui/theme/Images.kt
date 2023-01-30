package com.turtleteam.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.runtime.staticCompositionLocalOf
import com.turtleteam.ui.R

val LocalImages = staticCompositionLocalOf<Images> { error("images wasnt provided") }
sealed class Images(
    @DrawableRes val btnNext:Int,
    @DrawableRes val btnSelect:Int,
    @DrawableRes val selectGroup:Int,
    @DrawableRes val selectTeacher:Int,
    @DrawableRes val turtleLeft:Int,
    @DrawableRes val turtleRight:Int,
){
    object DayImages:Images(
        btnNext = R.drawable.btnnext,
        btnSelect = R.drawable.btnselect,
        selectGroup = R.drawable.selectgroup,
        selectTeacher = R.drawable.selectteacher,
        turtleLeft = R.drawable.turtleleft,
        turtleRight = R.drawable.turtleright
    )
    object NightImages:Images(
        btnNext = R.drawable.btnnextnight,
        btnSelect = R.drawable.btnselectnight,
        selectGroup = R.drawable.selectgroupnight,
        selectTeacher = R.drawable.selectteachernight,
        turtleLeft = R.drawable.turtleleftnight,
        turtleRight = R.drawable.turtlerightnight
    )
}
