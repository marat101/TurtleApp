package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository

class GetTeachersListUseCase(private val repository: TeachersRepository) {

    /**
     * Возвращает список всех преподавателей без разделения на закрепленные и незакрепленные
     */

    suspend fun execute(): List<String> = repository.getTeachersList()
}