package com.turtleteam.ui.theme_animator

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot

fun Modifier.getPositiion(
    onPositiionChanged: (Offset) -> Unit
) = this.onGloballyPositioned {
    val pos = it.positionInRoot()
    onPositiionChanged(
        pos.copy(
            x = pos.x + it.size.width / 2,
            y = pos.y + it.size.height / 2
        )
    )
}