package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.GroupsRepository

class GetGroupsListUseCase(private val repository: GroupsRepository) {

    /**
     * Возвращает все группы без разделения на закрепленные и незакрепленные
     */

    suspend fun execute(): List<String> = repository.getGroupsList()
}