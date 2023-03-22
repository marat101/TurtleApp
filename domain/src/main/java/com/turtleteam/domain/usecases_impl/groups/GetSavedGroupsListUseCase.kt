package com.turtleteam.domain.usecases_impl.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetNamesListUC
import com.turtleteam.domain.usecases.GetSavedNamesListUC

class GetSavedGroupsListUseCase(private val repository: ScheduleRepository): GetSavedNamesListUC {

    override suspend fun execute(): List<String>? = repository.getSavedNamesList()
}