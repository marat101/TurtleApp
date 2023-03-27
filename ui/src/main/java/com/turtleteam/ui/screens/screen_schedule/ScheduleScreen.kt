package com.turtleteam.ui.screens.screen_schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.model.other.States
import com.turtleteam.ui.R
import com.turtleteam.ui.screens.common.components.ErrorView
import com.turtleteam.ui.screens.screen_schedule.layouts.ScheduleLayout
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
            States.Error -> {
                ErrorView(Modifier.height(90.dp)) {
                    viewModel.getSchedule()
                }
            }
            States.Loading -> {
                //TODO StatesView
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp),
                    painter = painterResource(id = R.drawable.ic_refreshing),
                    contentDescription = null
                )
            }
            is States.Success -> {
            }
        }
        state.value.data?.let {
            ScheduleLayout(it)
        }
    }
    LaunchedEffect(null) {
        viewModel.getSchedule()
    }
}