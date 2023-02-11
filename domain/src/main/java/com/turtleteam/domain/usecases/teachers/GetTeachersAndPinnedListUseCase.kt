package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.model.teachersandgroups.NamesList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.GetListAndPinnedListUC

class GetTeachersAndPinnedListUseCase(private val repository: ScheduleRepository) :
    GetListAndPinnedListUC {

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