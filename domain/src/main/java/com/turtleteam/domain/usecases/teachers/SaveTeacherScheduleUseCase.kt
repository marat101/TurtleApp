package com.turtleteam.domain.usecases.teachers

import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.SaveScheduleUC

class SaveTeacherScheduleUseCase(private val repository: ScheduleRepository):SaveScheduleUC {

    override suspend fun execute(schedule: DaysList) = repository.saveSchedule(schedule)
}