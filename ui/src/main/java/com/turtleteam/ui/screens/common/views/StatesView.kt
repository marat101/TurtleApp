package com.turtleteam.ui.screens.common.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.model.other.States
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@Composable
fun BoxScope.TopErrorView(state: States) {

    val visible = remember { MutableTransitionState(false) }
    val text = remember { mutableStateOf("") }

    AnimatedVisibility(
        modifier = Modifier.align(Alignment.TopCenter),
        visibleState = visible,
        label = "",
        enter = slideInVertically(),
        exit = slideOutVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp)
                .background(Color.White.copy(alpha = 0.35F)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text.value,
                font = fontGanelas,
                color = TurtleTheme.color
            )
        }
    }
    LaunchedEffect(key1 = state, block = {
        when (state) {
            States.Error -> TODO()
            States.Loading -> TODO()
            States.Success -> TODO()
        }
    })
}

enum class State(val description: String) {
    ERROR("Не удалось загрузить расписание"),
    SUCCESS("Расписание обновлено"),
    LOADING("Обновление расписания"),
    SAVED("Не удалось обновить расписание"),
    HIDDEN("")
}