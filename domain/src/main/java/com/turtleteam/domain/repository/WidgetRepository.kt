package com.turtleteam.domain.repository

import com.turtleteam.domain.model.widget.ScheduleWidgetState

interface WidgetRepository {

    suspend fun getScheduleWidgetState(id: Int): ScheduleWidgetState

    suspend fun insertScheduleWidget(widgetState: ScheduleWidgetState)

    suspend fun deleteScheduleWidget(id: Int)
}