package com.turtleteam.domain.usecases_impl.groups

import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetListAndPinnedListUC

class GetGroupsAndPinnedListUseCase(
    private val repository: ScheduleRepository
) : GetListAndPinnedListUC {

    /**
     * Получение отфильтрованного списка всех групп
     */

    override suspend fun execute(): NamesList {
        val allgroups = repository.getNamesList().toMutableList()
        val pinnedGroups = repository.getPinnedList()

        allgroups.removeAll(pinnedGroups)

        return NamesList(pinnedGroups, allgroups)
    }
}