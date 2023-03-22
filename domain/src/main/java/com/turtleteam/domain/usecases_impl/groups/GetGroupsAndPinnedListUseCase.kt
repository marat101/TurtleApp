package com.turtleteam.domain.usecases_impl.groups

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetPinnedListUC

class GetGroupsAndPinnedListUseCase(
    private val repository: ScheduleRepository
) : GetPinnedListUC {

    override suspend fun execute(): List<String> {
        return repository.getPinnedList()
    }
}