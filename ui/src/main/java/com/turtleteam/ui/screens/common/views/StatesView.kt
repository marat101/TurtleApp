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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
            Text("Обновление расписания")
        }
    }
}

class ErrorViewState(
    val visibleState: MutableTransitionState<Boolean> = MutableTransitionState(true),
) {

    companion object{
        val ERROR = "Не удалось загрузить расписание"
        val SUCCESS = "Расписание обновлено"
        val LOADING = "Обновление расписания"
        val SAVED = "Не удалось обновить расписание"
    }

}