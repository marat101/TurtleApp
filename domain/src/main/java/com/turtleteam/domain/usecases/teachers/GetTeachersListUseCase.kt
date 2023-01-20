package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.android.turtleapp.data.repository.interfaces.TeachersRepository

class GetTeachersListUseCase(private val repository: TeachersRepository) {

    suspend fun execute(): List<String> = repository.getTeachersList()
}