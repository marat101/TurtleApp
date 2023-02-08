package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.SaveScheduleUC

class SaveGroupScheduleUseCase(private val repository: ScheduleRepository):SaveScheduleUC {

    override suspend fun execute(schedule: DaysList) = repository.saveSchedule(schedule)
}