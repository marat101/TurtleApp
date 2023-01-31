package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.utils.GetListAndPinnedListUC

class GetGroupsAndPinnedListUseCase(
    private val repository: GroupsRepository
) : GetListAndPinnedListUC {

    /**
     * Получение отфильтрованного списка всех групп
     */

    override suspend fun execute(): NamesList {
        val allgroups = repository.getGroupsList().toMutableList()
        val pinnedGroups = repository.getPinnedList()

        allgroups.removeAll(pinnedGroups)

        return NamesList(pinnedGroups, allgroups)
    }
}