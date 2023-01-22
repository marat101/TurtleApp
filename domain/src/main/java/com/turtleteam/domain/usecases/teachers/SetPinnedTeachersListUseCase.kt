package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.model.NamesList

class SetPinnedTeachersListUseCase(private val repository: TeachersRepository) {

    /**
     * Юзкейс для Закрепления\Открепления преподавателей
     *
     * currentList - список который используется
     * item - преподаватель который закрепится\открепится
     *
     * Возвращает обновлённый список
     */

    fun execute(currentList: NamesList, item: String): NamesList {
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