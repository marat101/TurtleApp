package com.turtleteam.widget_schedule.repository

import android.content.Context
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.WidgetRepository
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class WidgetRepositoryImpl(context: Context) : WidgetRepository, KoinComponent {

    companion object {
        const val WIDGET_PREFS = "widgetprefs"
        const val WIDGET = "widgetdata"
    }

    private val json: Json by inject()
    private val widgetPrefs = context.getSharedPreferences(WIDGET_PREFS, Context.MODE_PRIVATE)

    override fun getScheduleWidgetState(): ScheduleWidgetState {
        val state = widgetPrefs.getString(WIDGET, "")
        return try { json.decodeFromString(ScheduleWidgetState.serializer(), state!!)} catch (e: Throwable){
            ScheduleWidgetState.empty()
        }
    }

    override fun insertScheduleWidget(widgetState: ScheduleWidgetState) {
        val stateStr = json.encodeToString(ScheduleWidgetState.serializer(), widgetState)
        widgetPrefs.edit().putString(WIDGET, stateStr).apply()
    }

    override fun deleteScheduleWidget() {
        val stateStr = json.encodeToString(ScheduleWidgetState.serializer(),ScheduleWidgetState.empty())
        widgetPrefs.edit().putString(WIDGET, stateStr).apply()
    }
}