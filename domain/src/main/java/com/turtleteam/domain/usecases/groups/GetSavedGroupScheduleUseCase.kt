package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.utils.GetSavedScheduleUC

class GetSavedGroupScheduleUseCase(private val repository: GroupsRepository):GetSavedScheduleUC {

    override suspend fun execute(name: String) = repository.getSavedSchedule(name)
}