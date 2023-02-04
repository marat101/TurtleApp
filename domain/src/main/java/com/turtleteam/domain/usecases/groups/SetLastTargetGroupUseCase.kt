package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.utils.SetLastTargetUC

class SetLastTargetGroupUseCase(private val repository: GroupsRepository): SetLastTargetUC {
    override fun execute(string:String){
        repository.setLastTargetGroup(string)
    }
}