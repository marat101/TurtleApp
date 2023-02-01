package com.turtleteam.ui.screens.schedulelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.callschedule.CallsItem
import com.turtleteam.ui.theme.JetTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleListScreen(
    vModel:ScheduleListViewModel = koinViewModel()
) {
    val data = vModel.getData()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        data.forEach {
            ScheduleOneBlock(it)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ScheduleOneBlock(callsItem: CallsItem) {
    Column(
        modifier = Modifier
            .background(
                JetTheme.color.transparentBackground,
                RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ) {
        callsItem.schedule.forEachIndexed { index, it ->
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = it,
                color = if (index==0) JetTheme.color.titleText else JetTheme.color.secondText,
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}