package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.turtleteam.theme.R
import ru.turtleteam.theme.TurtleTheme

@Composable
fun HintBox(onCloseClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
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
            modifier = Modifier.weight(10F),
            text = "Удерживайте, чтобы закрепить расписание.",
            color = Color.Gray
        )
        IconButton(modifier = Modifier
            .weight(1F)
            .align(Alignment.Top)
            .size(15.dp),
            onClick = onCloseClick){
            Icon(
                modifier = Modifier.size(15.dp),
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}