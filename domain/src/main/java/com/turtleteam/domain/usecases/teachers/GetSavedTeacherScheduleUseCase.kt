package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.GroupsRepository

class GetSavedTeacherScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(name: String) = repository.getSavedSchedule(name)
}