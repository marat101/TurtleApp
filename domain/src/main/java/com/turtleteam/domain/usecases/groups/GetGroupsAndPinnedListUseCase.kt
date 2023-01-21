package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.domain.model.NamesList

class GetGroupsAndPinnedListUseCase(private val repository: GroupsRepository) {

    /**
     * Получение отфильтрованного списка всех групп
     */

    suspend fun execute(): NamesList {
        val allgroups = repository.getGroupsList().toMutableList()
        val pinnedGroups = repository.getPinnedList()

        allgroups.removeAll(pinnedGroups)

        return NamesList(pinnedGroups, allgroups)
    }
}