package com.turtleteam.domain.usecases.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.GetLastTargetUC

class GetLastTargetTeacherUseCase(private val repository: ScheduleRepository): GetLastTargetUC {
    override fun execute() = repository.getLastTargetName()
}