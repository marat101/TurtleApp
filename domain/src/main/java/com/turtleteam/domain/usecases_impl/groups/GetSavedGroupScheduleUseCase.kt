package com.turtleteam.domain.usecases_impl.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetSavedScheduleUC

class GetSavedGroupScheduleUseCase(private val repository: ScheduleRepository) :
    GetSavedScheduleUC {

    override suspend fun execute(name: String) = repository.getSavedSchedule(name)
}