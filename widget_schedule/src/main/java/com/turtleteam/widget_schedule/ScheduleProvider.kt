package com.turtleteam.widget_schedule

import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.GetSavedScheduleUC
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.domain.usecases.SaveScheduleUC
import kotlinx.coroutines.flow.last
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

interface ScheduleProvider {
    suspend fun getSchedule(name: String, isGroup: Boolean): DaysList
}

internal object ScheduleProviderImpl : ScheduleProvider, KoinComponent {
    override suspend fun getSchedule(name: String, isGroup: Boolean): DaysList {
        return try {
            val getSchedule: GetScheduleUC by inject(named(if (isGroup) "Groups" else "Teachers"))
            val saveSchedule: SaveScheduleUC by inject(named(if (isGroup) "Groups" else "Teachers"))
            val schedule = getSchedule.execute(name)
            runCatching { saveSchedule.execute(schedule) }
            schedule
        }catch (e: Throwable){
            println("Widget throwable $e")
            val getSchedule: GetSavedScheduleUC by inject(named(if (isGroup) "Groups" else "Teachers"))
            getSchedule.execute(name).last() ?: DaysList(emptyList(), name)
        }
    }
}