package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.model.NamesList

class GetTeachersAndPinnedListUseCase(private val repository: TeachersRepository) {

    /**
     * Получение отфильтрованного списка всех групп
     */

    suspend fun execute(): NamesList {
        val allgroups = repository.getTeachersList().toMutableList()
        val pinnedGroups = repository.getPinnedList()

        allgroups.removeAll(pinnedGroups)

        return NamesList(pinnedGroups, allgroups)
    }
}