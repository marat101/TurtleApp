package com.turtleteam.domain.usecases_impl.widget

import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.repository.WidgetRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class SaveScheduleWidget(private val repository: WidgetRepository) : KoinComponent {

    suspend fun execute(widgetState: ScheduleWidgetState) {
        repository.insertScheduleWidget(widgetState)
    }
}