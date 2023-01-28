package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.utils.GetSavedScheduleUC

class GetSavedTeacherScheduleUseCase(private val repository: TeachersRepository):GetSavedScheduleUC {

    override suspend fun execute(name: String) = repository.getSavedSchedule(name)
}