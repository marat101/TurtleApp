package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList

class GetGroupScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(name: String): States<DaysList> = repository.getSchedule(name)
}