package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.GetLastTargetUC

class GetLastTargetGroupUseCase(private val repository: ScheduleRepository): GetLastTargetUC {
    override fun execute() = repository.getLastTargetName()
}