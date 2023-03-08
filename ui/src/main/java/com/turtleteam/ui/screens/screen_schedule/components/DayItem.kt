package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@Composable
fun DayItem(day: Day) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(17.dp)
            .background(TurtleTheme.color.transparentBackground, TurtleTheme.shapes.medium)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 5.dp),
            fontSize = 22.sp,
            fontFamily = fontGanelas,
            color = TurtleTheme.color.titleText,
            text = day.day,
        )
        Column(
            modifier = Modifier.padding(horizontal = 15.dp).padding(bottom = 15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            day.apairs.forEach {
                PairItem(it, day.day)
            }
        }
    }
}