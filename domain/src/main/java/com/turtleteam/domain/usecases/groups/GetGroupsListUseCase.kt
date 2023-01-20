package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository

class GetGroupsListUseCase(private val repository: GroupsRepository) {

    suspend fun execute(): List<String> = repository.getGroupsList()
}