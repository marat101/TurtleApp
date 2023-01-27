package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.GroupsRepository

class GetSavedGroupScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(name: String) = repository.getSavedSchedule(name)
}