package com.turtleteam.domain.usecases_impl.teachers

import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.usecases.GetPinnedListUC

class GetTeachersAndPinnedListUseCase(private val repository: ScheduleRepository) :
    GetPinnedListUC {

    override suspend fun execute(): List<String> {
        return repository.getPinnedList()
    }
}