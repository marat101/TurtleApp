package com.android.turtleapp.data.local.entity

import com.android.turtleapp.data.model.schedule.Day
import kotlinx.serialization.Serializable


@Serializable
data class GroupsDaysList(
    val days: List<Day>,
    val name: String
)