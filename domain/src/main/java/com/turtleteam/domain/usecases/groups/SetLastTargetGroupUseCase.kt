package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.SetLastTargetUC

class SetLastTargetGroupUseCase(private val repository: ScheduleRepository): SetLastTargetUC {
    override fun execute(group:String){
        repository.setLastTargetName(group)
    }
}