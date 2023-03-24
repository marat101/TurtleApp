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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BoxScope.TopErrorView() {

    val state = remember { ErrorViewState() }
    AnimatedVisibility(
        modifier = Modifier.align(Alignment.TopCenter),
        visibleState = state.visibleState,
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
            Text(state.currentState.value.description)
        }
    }
    when (state.currentState.value) {
        State.HIDDEN -> {
            state.visibleState.targetState = false
        }
        else -> {
            state.visibleState.targetState = true
        }
    }
    LaunchedEffect(key1 = state, block = {
        state.currentState.value = State.ERROR
        delay(2000)
        state.currentState.value = State.HIDDEN
        delay(2000)
        state.currentState.value = State.SUCCESS
    })
}

enum class State(val description: String) {
    ERROR("Не удалось загрузить расписание"),
    SUCCESS("Расписание обновлено"),
    LOADING("Обновление расписания"),
    SAVED("Не удалось обновить расписание"),
    HIDDEN("")
}

data class ErrorViewState(
    val visibleState: MutableTransitionState<Boolean> = MutableTransitionState(false),
    val currentState: MutableState<State> = mutableStateOf(State.HIDDEN),
)