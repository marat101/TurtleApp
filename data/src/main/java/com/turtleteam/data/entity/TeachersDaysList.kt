package com.android.turtleapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.domain.model.Schedule
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class TeachersDaysList(
    override val days: List<Day>,
    @PrimaryKey override val name: String
) : Schedule
