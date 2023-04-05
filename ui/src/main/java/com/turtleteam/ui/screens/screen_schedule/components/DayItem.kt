package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.ui.theme.fontQanelas

@Composable
fun DayItem(day: Day) {
    Card(
        modifier = Modifier
            .padding(top = 35.dp)
            .height(30.dp)
            .zIndex(1f),
        backgroundColor = Color(0xFFF2F6E8),
        shape = RoundedCornerShape(12.dp),
        elevation = 3.dp,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
        ) {
            Icon(
                painter = painterResource(id = com.turtleteam.ui.R.drawable.ic_arrow_left),
                contentDescription = "",
                tint = Color(0xFF417B65),
                modifier = Modifier
                    .size(14.dp)
                    .padding(end = 9.dp)
            )
            Text(
                text = day.day, style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontQanelas,
                    color = Color(0xFF417B65)
                )
            )
            Icon(
                painter = painterResource(id = com.turtleteam.ui.R.drawable.ic_arrow_right),
                contentDescription = "",
                tint = Color(0xFF417B65),
                modifier = Modifier
                    .size(14.dp)
                    .padding(start = 9.dp)
            )
        }
    }
}
