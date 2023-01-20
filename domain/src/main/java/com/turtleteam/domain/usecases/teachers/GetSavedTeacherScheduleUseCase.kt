package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository

class GetSavedTeacherScheduleUseCase(private val repository: TeachersRepository) {

    suspend fun execute(name: String) = repository.getSavedSchedule(name)
}