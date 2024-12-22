package com.turtleteam.domain.usecases_impl.widget

import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.WidgetRepository
import org.koin.core.component.KoinComponent

class SaveScheduleWidget(private val repository: WidgetRepository) : KoinComponent {

    fun execute(widgetId: Int, widgetState: ScheduleWidgetState) {
        repository.upsertWidget(widgetId, widgetState)
    }
}