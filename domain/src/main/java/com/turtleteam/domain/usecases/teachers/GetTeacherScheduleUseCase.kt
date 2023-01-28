package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.utils.GetScheduleUC

class GetTeacherScheduleUseCase(private val repository: TeachersRepository) : GetScheduleUC {

    override suspend fun execute(name: String): States<DaysList> = repository.getSchedule(name)
}