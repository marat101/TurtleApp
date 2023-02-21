package com.turtleteam.domain.usecases_impl.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.SetLastTargetUC

class SetLastTargetGroupUseCase(private val repository: ScheduleRepository) : SetLastTargetUC {
    override fun execute(group: String) {
        repository.setLastTargetName(group)
    }
}