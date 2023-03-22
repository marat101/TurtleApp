package com.turtleteam.data.entity


import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.domain.model.other.StatefulModel
import kotlinx.serialization.Serializable

@Serializable
data class TeachersDaysList(
    val days: List<Day>,
    val name: String
)
