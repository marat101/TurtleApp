package com.turtleteam.domain.model.widget

import com.turtleteam.domain.model.schedule.DaysList
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleWidgetState(
    val schedule: DaysList,
    val page: Int,
    val isGroup: Boolean
) {
    companion object {
        fun empty() = ScheduleWidgetState(DaysList(emptyList(), ""), 0, true)
    }
}