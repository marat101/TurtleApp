package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.model.schedule.DaysList

class SaveTeacherScheduleUseCase(private val repository: TeachersRepository) {

    suspend fun execute(schedule: DaysList) = repository.saveSchedule(schedule)
}