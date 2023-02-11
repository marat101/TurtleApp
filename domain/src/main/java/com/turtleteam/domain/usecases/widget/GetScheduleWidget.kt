package com.turtleteam.domain.usecases.widget

import com.turtleteam.domain.repository.WidgetRepository

class GetScheduleWidget(private val repository: WidgetRepository) {

    suspend fun execute(id: Int) = repository.getScheduleWidgetState(id)
}