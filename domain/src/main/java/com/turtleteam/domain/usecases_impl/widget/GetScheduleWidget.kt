package com.turtleteam.domain.usecases_impl.widget

import com.turtleteam.domain.repository.WidgetRepository

class GetScheduleWidget(private val repository: WidgetRepository) {

    fun execute(widgetId: Int) = repository.getWidgetStateById(widgetId)
}