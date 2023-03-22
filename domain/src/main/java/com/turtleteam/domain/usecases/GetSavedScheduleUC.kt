package com.turtleteam.domain.usecases

import com.turtleteam.domain.model.schedule.DaysList

interface GetSavedScheduleUC {
    suspend fun execute(name: String): DaysList?
}