package com.turtleteam.ui.screens.screen_schedule.components

import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.PairsList
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas
import com.turtleteam.ui.utils.Calendar
import java.time.LocalTime
import java.util.*

@Composable
fun PairItem(pairs: PairsList, day: String) {

    val TIME_ICON = "⏳"
    val DOCTRINE_ICON = "\uD83D\uDCD6"
    val TEACHER_ICON = "\uD83C\uDF93"
    val AUDITORIA_ICON = "\uD83D\uDD11"
    val CORPUS_ICON = "\uD83C\uDFE2"


    pairs.apair.forEach { item ->

        val currentPair = remember { mutableStateOf(false) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val calendar = Calendar()
            val current_day = day.removeRange(0,2).split(" ")
            val start_pair = item.start.split(":")
            val end_pair = item.end.split(":")
            val current_hour = Date().hours.toString()
            val current_minute = Date().minutes.toString()
            val start_time = LocalTime.parse("${start_pair[0]}:${start_pair[1]}")
            val end_time = LocalTime.parse("${end_pair[0]}:${end_pair[1]}")
            val current_time =
                LocalTime.parse(
                    "${if (current_hour.length > 1) current_hour else "0$current_hour"}:${
                        if (current_minute.length > 1) current_minute else "0$current_minute"
                    }"
                )
            if (current_day[0] == calendar.changeDay()) {
                if (current_time in start_time..end_time) {
                    currentPair.value = true
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(TurtleTheme.shapes.medium),
            horizontalArrangement = Arrangement.spacedBy(7.dp)
        ) {
            if (currentPair.value)
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .background(
                            TurtleTheme.color.bottomSheetView,
                            RoundedCornerShape(
                                topEnd = 3.dp,
                                bottomEnd = 3.dp,
                            )
                        )
                )

            Column(modifier = Modifier.padding(vertical = 5.dp)) {
                Text(
                    text = "${item.number} ПАРА",
                    fontSize = 17.sp,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.titleText
                )
                Text(
                    text = "$TIME_ICON ${item.start} - ${item.end}",
                    fontSize = 17.sp,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.secondText
                )
                Text(
                    text = "$DOCTRINE_ICON ${item.doctrine}",
                    fontSize = 17.sp,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.secondText,
                )
                Text(
                    text = "$TEACHER_ICON ${item.teacher}",
                    fontSize = 17.sp,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.secondText,
                )
                Text(
                    text = "$AUDITORIA_ICON ${item.auditoria}",
                    fontSize = 17.sp,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.secondText,
                )
                Text(
                    text = "$CORPUS_ICON ${item.corpus}",
                    fontSize = 17.sp,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.secondText,
                )
            }
        }
    }
}