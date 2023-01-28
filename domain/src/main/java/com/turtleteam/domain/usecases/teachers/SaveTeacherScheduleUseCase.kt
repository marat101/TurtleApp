package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.utils.SaveScheduleUC

class SaveTeacherScheduleUseCase(private val repository: TeachersRepository):SaveScheduleUC {

    override suspend fun execute(schedule: DaysList) = repository.saveSchedule(schedule)
}