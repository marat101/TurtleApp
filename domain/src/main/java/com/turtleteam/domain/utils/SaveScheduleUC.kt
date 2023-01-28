package com.turtleteam.domain.utils

import com.turtleteam.domain.model.schedule.DaysList

interface SaveScheduleUC {
    suspend fun execute(schedule: DaysList)
}