package com.turtleteam.domain.usecases

import com.turtleteam.domain.model.schedule.DaysList
import kotlinx.coroutines.flow.Flow

interface GetSavedScheduleUC {
    fun execute(name: String): Flow<DaysList?>
}