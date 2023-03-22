package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetNamesListUC

class GetTeachersListUseCase(private val repository: ScheduleRepository): GetNamesListUC {

    override suspend fun execute(): List<String> = repository.getNamesList()
}