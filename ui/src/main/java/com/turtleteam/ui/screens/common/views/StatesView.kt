package com.turtleteam.ui.screens.common.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.other.States
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@Composable
fun BoxScope.TopErrorView(state: States) {

    val visible = remember { MutableTransitionState(true) }
    val text = remember { mutableStateOf(State.LOADING.description) }

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
                .height(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = text.value,
                    fontFamily = fontGanelas,
                    color = TurtleTheme.color.bottomSheetView,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 20.sp
                )
            }
        }
    }
    LaunchedEffect(key1 = state, block = {
        when (state) {
            //TODO
            States.Error -> {}
            States.Loading -> {}
            States.Success -> {}
        }
    })
}

enum class State(val description: String) {
    ERROR("Не удалось загрузить расписание"),
    SUCCESS("Расписание обновлено"),
    LOADING("Обновление расписания"),
    SAVED("Не удалось обновить"),
    HIDDEN("")
}