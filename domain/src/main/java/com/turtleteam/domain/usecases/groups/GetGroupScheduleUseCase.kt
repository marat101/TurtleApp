package com.turtleteam.domain.usecases.groups

import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.GroupsRepository

class GetGroupScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(name: String): States<DaysList> = repository.getSchedule(name)
}