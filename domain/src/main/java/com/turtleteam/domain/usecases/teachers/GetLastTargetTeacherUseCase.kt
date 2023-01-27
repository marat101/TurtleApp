package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.utils.GetLastTargetUC

class GetLastTargetTeacherUseCase(private val repository: TeachersRepository): GetLastTargetUC {
    override fun execute() = repository.getLastTargetTeacher()
}