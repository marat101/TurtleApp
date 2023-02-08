package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.GetScheduleUC

class GetGroupScheduleUseCase(private val repository: ScheduleRepository):GetScheduleUC {

    override suspend fun execute(name: String): States<DaysList> = repository.getSchedule(name)
}