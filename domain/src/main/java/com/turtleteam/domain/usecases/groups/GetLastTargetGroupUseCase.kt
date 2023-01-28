package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.utils.GetLastTargetUC

class GetLastTargetGroupUseCase(private val repository: GroupsRepository): GetLastTargetUC {
    override fun execute() = repository.getLastTargetGroup()
}