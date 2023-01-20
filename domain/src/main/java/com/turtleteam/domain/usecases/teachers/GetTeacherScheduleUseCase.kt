package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList

class GetTeacherScheduleUseCase(private val repository: TeachersRepository) {

    suspend fun execute(name: String): States<DaysList> = repository.getSchedule(name)
}