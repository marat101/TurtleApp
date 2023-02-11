package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.model.teachersandgroups.NamesList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.SetPinnedListUC

class SetPinnedTeachersListUseCase(private val repository: ScheduleRepository) : SetPinnedListUC {

    /**
     * Юзкейс для Закрепления\Открепления преподавателей
     *
     * currentList - список который используется
     * item - преподаватель который закрепится\открепится
     *
     * Возвращает обновлённый список
     */

    override fun execute(currentList: NamesList, item: String): NamesList {
        val list = currentList.pinned.toMutableList()
        val list2 = currentList.groups.toMutableList()
        return if (currentList.groups.contains(item)) {
            list.add(item)
            list2.removeAll(list)
            repository.savePinnedList(list)
            NamesList(list, list2)
        } else {
            list.remove(item)
            list2.add(item)
            repository.savePinnedList(list)
            NamesList(list, list2)
        }
    }
}