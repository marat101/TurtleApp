package com.turtleteam.ui.screens.screen_schedule

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.model.other.States
import com.turtleteam.ui.screens.common.components.ErrorView
import com.turtleteam.ui.screens.screen_schedule.layouts.ScheduleLayout
import com.turtleteam.ui.theme.LocalColors
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleScreen(
    name: String,
    isTeacher: Boolean,
    viewModel: ScheduleViewModel = getViewModel(
        named(if (isTeacher) "Teachers" else "Groups"),
        parameters = {
            parametersOf(name)
        })
) {
    val state = viewModel.state.collectAsState()
    val loading = remember { derivedStateOf { state.value.loadingState == States.Loading } }
    val refreshState = rememberPullRefreshState(
        refreshing = loading.value,
        onRefresh = { viewModel.getSchedule() })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(refreshState),
        contentAlignment = Alignment.Center
    ) {
        if (state.value.loadingState == States.Error && state.value.data?.days.isNullOrEmpty()) {
            ErrorView(Modifier.height(90.dp)) {
                viewModel.getSchedule()
            }
        }
        state.value.data?.let {
            ScheduleLayout(it)
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = state.value.loadingState == States.Loading,
            state = refreshState,
            backgroundColor = LocalColors.current.dateBackground,
            contentColor = LocalColors.current.textColor
        )
    }
}