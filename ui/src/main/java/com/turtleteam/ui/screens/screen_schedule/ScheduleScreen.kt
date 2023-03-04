package com.turtleteam.ui.screens.screen_schedule

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ScheduleScreen(name: String,
                   isTeacher: Boolean,
) {
    Text(text = name + isTeacher.toString())
}