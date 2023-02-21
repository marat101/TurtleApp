package com.turtleteam.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val LocalShapes = staticCompositionLocalOf<Shapes> { error("shapes wasnt provided") }

val turtleShapes = Shapes(
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(0.dp)
)