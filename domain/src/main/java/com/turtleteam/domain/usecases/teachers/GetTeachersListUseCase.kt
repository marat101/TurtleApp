package com.turtleteam.domain.usecases.teachers

import com.turtleteam.domain.repository.ScheduleRepository

class GetTeachersListUseCase(private val repository: ScheduleRepository) {

    /**
     * Возвращает список всех преподавателей без разделения на закрепленные и незакрепленные
     */

    suspend fun execute(): List<String> = repository.getNamesList()
}