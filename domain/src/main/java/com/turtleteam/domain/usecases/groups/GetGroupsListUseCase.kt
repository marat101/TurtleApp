package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.ScheduleRepository

class GetGroupsListUseCase(private val repository: ScheduleRepository) {

    /**
     * Возвращает все группы без разделения на закрепленные и незакрепленные
     */

    suspend fun execute(): List<String> = repository.getNamesList()
}