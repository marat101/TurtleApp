package com.turtleteam.domain.usecases.groups

import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.domain.model.schedule.DaysList

class SaveGroupScheduleUseCase(private val repository: GroupsRepository) {

    suspend fun execute(schedule: DaysList) = repository.saveSchedule(schedule)
}