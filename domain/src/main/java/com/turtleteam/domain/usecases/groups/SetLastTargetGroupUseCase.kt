package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository

class SetLastTargetGroupUseCase(private val repository: GroupsRepository) {
    fun execute(group:String){
        repository.setLastTargetGroup(group)
    }
}