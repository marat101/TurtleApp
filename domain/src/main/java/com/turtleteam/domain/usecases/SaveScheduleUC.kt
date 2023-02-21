package com.turtleteam.domain.usecases

import com.turtleteam.domain.model.schedule.DaysList

interface SaveScheduleUC {
    suspend fun execute(schedule: DaysList)
}