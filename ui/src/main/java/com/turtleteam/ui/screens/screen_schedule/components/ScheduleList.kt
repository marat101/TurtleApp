package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.turtleteam.domain.model.schedule.DaysList

@Composable
fun ScheduleList(value: DaysList) {

    val visible = remember { Animatable(0F) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .alpha(visible.value),
    ) {
        items(value.days) {
            DayItem(it)
        }
    }
    LaunchedEffect(null) {
        visible.animateTo(1F, tween(400))
    }
}