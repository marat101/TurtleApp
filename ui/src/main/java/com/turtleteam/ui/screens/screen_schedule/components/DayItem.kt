package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.fontQanelas

@Composable
fun DayItem(day: Day) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .padding(top = 35.dp)
                .width(210.dp)
                .height(30.dp)
                .background(color = Color(0xFFF2F6E8), shape = RoundedCornerShape(12.dp)),
            horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_left),
                contentDescription = "",
                tint = Color(0xFF417B65),
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 3.dp, end = 5.dp)
            )
            Text(
                text = day.day, style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = fontQanelas,
                    color = Color(0xFF417B65)
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = "",
                tint = Color(0xFF417B65),
                modifier = Modifier
                    .size(20.dp)
                    .padding(end = 3.dp, start = 5.dp)
            )
        }

        LazyColumn {
            items(items = day.apairs) { pair ->
                Column(Modifier.padding(top=14.dp)) {
                    PairItem(pairs = pair)
                }
            }
        }
    }
}
