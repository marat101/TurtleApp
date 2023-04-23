package com.turtleteam.domain.repository

import com.turtleteam.domain.model.widget.ScheduleWidgetState

interface WidgetRepository {

    fun getScheduleWidgetState(): ScheduleWidgetState

    fun insertScheduleWidget(widgetState: ScheduleWidgetState)

    fun deleteScheduleWidget()
}