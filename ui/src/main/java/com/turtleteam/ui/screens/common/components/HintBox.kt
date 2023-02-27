package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.theme.TurtleTheme

@Composable
fun HintBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(BorderStroke((2.5).dp, TurtleTheme.color.secondText), TurtleTheme.shapes.medium)
    ) {

    }
}