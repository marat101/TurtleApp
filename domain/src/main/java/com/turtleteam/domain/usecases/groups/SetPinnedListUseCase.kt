package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.domain.model.NamesList

class SetPinnedListUseCase(private val repository: GroupsRepository) {

    /**
     * Юзкейс для Закрепления\Открепления закреплённых групп
     *
     * allGroups - список который используется
     * item - группа которая закрепится\открепится
     *
     * Вовзращает обновлённый список
     */

    fun execute(allGroupsList: NamesList, item: String): NamesList {
        val list = allGroupsList.pinned.toMutableList()
        val list2 = allGroupsList.groups.toMutableList()
        return if (allGroupsList.groups.contains(item)) {
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