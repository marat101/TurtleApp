package com.turtleteam.domain.model.widget

import com.turtleteam.domain.model.Schedule

data class ScheduleWidgetState(
    val id: Int,
    val schedule: Schedule,
    val page: Int,
    val isGroups: Boolean
)
