package com.turtleteam.domain.usecases.teachers

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.domain.utils.SetLastTargetUC

class SetLastTargetTeacherUseCase(private val repository: TeachersRepository): SetLastTargetUC {
    override fun execute(string:String){
        repository.setLastTargetTeacher(string)
    }
}