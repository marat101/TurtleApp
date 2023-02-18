package com.turtleteam.domain.model.widget

import com.turtleteam.domain.model.schedule.DaysList

data class ScheduleWidgetState(
    val id: Int,
    val schedule: DaysList,
    val page: Int,
    val isGroup: Boolean
)