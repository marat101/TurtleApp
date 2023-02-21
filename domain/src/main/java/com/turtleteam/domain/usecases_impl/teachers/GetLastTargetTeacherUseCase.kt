package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetLastTargetUC

class GetLastTargetTeacherUseCase(private val repository: ScheduleRepository) : GetLastTargetUC {
    override fun execute() = repository.getLastTargetName()
}