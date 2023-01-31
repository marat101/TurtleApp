package com.turtleteam.ui.screens.schedulelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.callschedule.CallsItem
import com.turtleteam.ui.TextWithFont
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleListScreen(
    vModel:ScheduleListViewModel = koinViewModel()
) {
    val data = vModel.getData()
    Column() {
        data.forEach {
            ScheduleOneBlock(it)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ScheduleOneBlock(callsItem: CallsItem) {
    callsItem.schedule.forEach {
        TextWithFont(text = it, color = Color.Red, textSize = 12.sp)
    }
}