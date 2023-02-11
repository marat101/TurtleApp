package com.android.turtleapp.data.local.entity

import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.domain.model.other.Schedule
import kotlinx.serialization.Serializable


@Serializable
data class GroupsDaysList(
    override val days: List<Day>,
    override val name: String
) : Schedule