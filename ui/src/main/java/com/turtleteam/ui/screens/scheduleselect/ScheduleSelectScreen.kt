package com.turtleteam.ui.screens.scheduleselect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleSelectScreen(
    navController: NavHostController,
    viewModel: ScheduleSelectViewModel = koinViewModel()
) {

    val groups = viewModel.schedule.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = groups.value.toString(),
            color = Color.Black)
            Button(onClick = { viewModel.getGroupsSchedule("ИС-23") }) {
                Text(text = "Получить расписание")
            }
            Button(onClick = { viewModel.saveSchedule(viewModel.schedule.value) }) {
                Text(text = "Сохранить расписание")
            }
            Button(onClick = { viewModel.getSavedSchedule("ИС-23") }) {
                Text(text = "Получить сохранённое расписание")
            }
        }
    }
}