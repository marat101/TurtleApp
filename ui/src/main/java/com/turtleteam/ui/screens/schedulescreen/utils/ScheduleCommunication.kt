package com.turtleteam.ui.screens.schedulescreen.utils

import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.utils.Communication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleCommunication: Communication<States<DaysList>> {
    private val flow = MutableStateFlow<States<DaysList>>(States.Loading)
    override fun map(value: States<DaysList>) {
        flow.value = value
    }

    override fun observe(): StateFlow<States<DaysList>> {
        return flow.asStateFlow()
    }
}