package com.turtleteam.domain.usecases_impl.widget

import com.turtleteam.domain.repository.WidgetRepository

class GetScheduleWidget(private val repository: WidgetRepository) {

    suspend fun execute(id: Int) = repository.getScheduleWidgetState(id)
}