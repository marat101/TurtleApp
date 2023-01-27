package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.GroupsRepository

class SaveGroupScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(schedule: DaysList) = repository.saveSchedule(schedule)
}