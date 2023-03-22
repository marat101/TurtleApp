package com.turtleteam.ui.screens.screen_schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.model.other.States
import com.turtleteam.ui.screens.common.components.ErrorView
import com.turtleteam.ui.screens.screen_schedule.components.ScheduleList
import com.turtleteam.ui.theme.TurtleTheme
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state.value.loadingState) {
            States.Error,
            States.NotFoundError -> {
                ErrorView(Modifier.height(90.dp)) {
                    viewModel.getSchedule()
                }
            }
            States.Loading -> {
                CircularProgressIndicator(color = TurtleTheme.color.bottomSheetView)
            }
            is States.Success -> {
                state.value.data?.let{
                    ScheduleList(it)
                }
            }
        }
    }
    LaunchedEffect(null) {
        viewModel.getSchedule()
    }
}