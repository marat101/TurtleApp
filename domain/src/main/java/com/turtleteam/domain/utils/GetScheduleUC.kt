package com.turtleteam.domain.utils

import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.schedule.DaysList

interface GetScheduleUC {
    suspend fun execute(name: String): States<DaysList>
}