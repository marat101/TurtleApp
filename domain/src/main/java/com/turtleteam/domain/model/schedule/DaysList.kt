package com.turtleteam.domain.model.schedule

import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.domain.model.Schedule
import kotlinx.serialization.Serializable

@Serializable
data class DaysList(
    override val days: List<Day>,
    override val name: String
) : Schedule