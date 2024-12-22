package com.turtleteam.domain.repository

import com.turtleteam.domain.model.widget.ScheduleWidgetState

interface WidgetRepository {

    fun getWidgetStateById(widgetId: Int): ScheduleWidgetState?

    fun upsertWidget(widgetId: Int, widgetState: ScheduleWidgetState)

    fun deleteWidgetById(widgetId: Int)

    fun getAllWidgetsIds(): Set<Int>
}