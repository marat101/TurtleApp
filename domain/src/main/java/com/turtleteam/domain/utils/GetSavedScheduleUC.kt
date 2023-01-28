package com.turtleteam.domain.utils

import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList

interface GetSavedScheduleUC {
    suspend fun execute(name: String): States<DaysList>

}