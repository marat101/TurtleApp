package com.turtleteam.domain.usecases.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.utils.SetLastTargetUC

class SetLastTargetTeacherUseCase(private val repository: ScheduleRepository): SetLastTargetUC {
    override fun execute(teacher:String){
        repository.setLastTargetName(teacher)
    }
}