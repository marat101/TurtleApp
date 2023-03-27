package com.turtleteam.ui.screens.screen_schedule.layouts

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.screens.screen_schedule.components.DayItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ScheduleLayout(value: DaysList) {

    val visible = remember { mutableStateOf(false) }

    AnimatedVisibility(
        visible = visible.value,
        enter = scaleIn(
            initialScale = 0.6F,
            animationSpec = tween(
                delayMillis = 150,
                durationMillis = 150,
                easing = LinearEasing
            )
        ) + fadeIn(
            initialAlpha = 0F,
            animationSpec = tween(
                delayMillis = 150,
                durationMillis = 450,
                easing = LinearEasing
            )
        )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(top = 17.dp)
        ) {
            items(value.days) {
                DayItem(it)
            }
        }
    }
    LaunchedEffect(key1 = visible, block = {
        delay(150)
        visible.value = true
    })
}