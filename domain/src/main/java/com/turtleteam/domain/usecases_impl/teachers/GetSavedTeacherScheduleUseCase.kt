package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetSavedScheduleUC

class GetSavedTeacherScheduleUseCase(private val repository: ScheduleRepository) :
    GetSavedScheduleUC {

    override suspend fun execute(name: String) = repository.getSavedSchedule(name)
}