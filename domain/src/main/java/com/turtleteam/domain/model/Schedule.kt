package com.turtleteam.domain.model

import com.android.turtleapp.data.model.schedule.Day

interface Schedule {
    val days: List<Day>
    val name: String
}