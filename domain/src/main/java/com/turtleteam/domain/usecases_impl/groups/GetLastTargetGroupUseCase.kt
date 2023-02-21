package com.turtleteam.domain.usecases_impl.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetLastTargetUC

class GetLastTargetGroupUseCase(private val repository: ScheduleRepository) : GetLastTargetUC {
    override fun execute() = repository.getLastTargetName()
}