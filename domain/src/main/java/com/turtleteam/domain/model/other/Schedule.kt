package com.turtleteam.domain.model.other

import com.android.turtleapp.data.model.schedule.Day

interface Schedule {
    val days: List<Day>
    val name: String
}