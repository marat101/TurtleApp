package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.TurtleTheme

@Composable
fun HintBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .border(BorderStroke((2.5).dp, TurtleTheme.color.secondText), TurtleTheme.shapes.medium)
            .padding(13.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_add_favorites),
            contentDescription = null
        )
        Text(
            text = "Удерживайте, чтобы закрепить расписание.",
            color = Color.Gray
        )
    }
}