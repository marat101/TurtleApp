package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository

class GetLastTargetGroupUseCase(private val repository: GroupsRepository) {
    fun execute() = repository.getLastTargetGroup()
}