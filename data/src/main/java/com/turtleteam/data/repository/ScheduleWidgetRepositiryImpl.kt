package com.turtleteam.data.repository

import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.WidgetRepository
import com.turtleteam.turtle_database.dao.ScheduleWidgetDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ScheduleWidgetRepositiryImpl(
    private val widgetDao: ScheduleWidgetDao
) : WidgetRepository {

    override suspend fun getScheduleWidgetState(id: Int): ScheduleWidgetState {
        val state = widgetDao.getWidgetById(id)
        return ScheduleWidgetState(
            state.id.toInt(),
            Json.decodeFromString(state.schedule),
            state.page.toInt(),
            state.isGroup == 1L
        )
    }

    override suspend fun insertScheduleWidget(widgetState: ScheduleWidgetState) {
        widgetDao.insertWidget(
            widgetState.id,
            widgetState.schedule,
            widgetState.page,
            widgetState.isGroup
        )
    }

    override suspend fun deleteScheduleWidget(id: Int) {
        widgetDao.deleteWidget(id)
    }
}