package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetSavedNamesListUC

class GetSavedTeachersListUseCase(private val repository: ScheduleRepository): GetSavedNamesListUC {

    override suspend fun execute(): List<String>? = repository.getSavedNamesList()
}