package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.SetLastTargetUC

class SetLastTargetTeacherUseCase(private val repository: ScheduleRepository) : SetLastTargetUC {
    override fun execute(teacher: String) {
        repository.setLastTargetName(teacher)
    }
}