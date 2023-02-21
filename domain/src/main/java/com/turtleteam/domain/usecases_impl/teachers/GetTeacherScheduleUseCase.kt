package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetScheduleUC

class GetTeacherScheduleUseCase(private val repository: ScheduleRepository) : GetScheduleUC {

    override suspend fun execute(name: String): States<DaysList> = repository.getSchedule(name)
}