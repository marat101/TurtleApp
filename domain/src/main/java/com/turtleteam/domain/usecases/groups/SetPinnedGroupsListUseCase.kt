package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.utils.SetPinnedListUC

class SetPinnedGroupsListUseCase(private val repository: GroupsRepository): SetPinnedListUC {

    /**
     * Юзкейс для Закрепления\Открепления групп
     *
     * currentList - список который используется
     * item - группа которая закрепится\открепится
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