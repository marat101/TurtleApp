package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.PairsList
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@Composable
fun ColumnScope.PairItem(pairs: PairsList) {

    val TIME_ICON = "⏳"
    val DOCTRINE_ICON = "\uD83D\uDCD6"
    val TEACHER_ICON = "\uD83C\uDF93"
    val AUDITORIA_ICON = "\uD83D\uDD11"
    val CORPUS_ICON = "\uD83C\uDFE2"


    pairs.apair.forEach { item ->
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(10.dp)
                    .background(
                        TurtleTheme.color.bottomSheetView,
                        RoundedCornerShape(
                            topEnd = 2.dp,
                            topStart = 10.dp,
                            bottomEnd = 2.dp,
                            bottomStart = 10.dp
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