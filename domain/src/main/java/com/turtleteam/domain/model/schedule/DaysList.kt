package com.turtleteam.domain.model.schedule

import com.android.turtleapp.data.model.schedule.Day
import kotlinx.serialization.Serializable

@Serializable
data class DaysList(
    val days: List<Day>,
    val name: String
)