package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository

class GetSavedGroupScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(name: String) = repository.getSavedSchedule(name)
}