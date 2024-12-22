package com.turtleteam.widget_schedule.repository

import android.content.Context
import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.WidgetRepository
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class WidgetRepositoryImpl(context: Context) : WidgetRepository, KoinComponent {

    companion object {
        const val WIDGET_PREFS = "WIDGETPREFS"
        const val WIDGET_IDS = "WIDGETIDS"
    }

    private val json: Json by inject()
    private val widgetPrefs = context.getSharedPreferences(WIDGET_PREFS, Context.MODE_PRIVATE)

    override fun getWidgetStateById(widgetId: Int): ScheduleWidgetState? {
        val state = widgetPrefs.getString(widgetId.toString(), null) ?: return null
        saveWidgetId(widgetId)
        return json.decodeFromString(ScheduleWidgetState.serializer(), state)
    }

    override fun upsertWidget(widgetId: Int, widgetState: ScheduleWidgetState) {
        val stateStr = json.encodeToString(ScheduleWidgetState.serializer(), widgetState)
        saveWidgetId(widgetId)
        widgetPrefs.edit().putString(widgetId.toString(), stateStr).apply()
    }

    override fun deleteWidgetById(widgetId: Int) {
        deleteWidgetId(widgetId)
        widgetPrefs.edit().remove(widgetId.toString()).apply()
    }

    override fun getAllWidgetsIds(): Set<Int> {
        val ids = widgetPrefs.getStringSet(WIDGET_IDS, setOf()) ?: setOf<String?>()
        return ids.mapNotNull { it?.toInt() }.toSet()
    }

    private fun saveWidgetId(id: Int) {
        val ids = (widgetPrefs.getStringSet(WIDGET_IDS, setOf()) ?: setOf<String?>()).toMutableSet()
        if (ids.add(id.toString())) widgetPrefs.edit().putStringSet(WIDGET_IDS, ids).apply()
    }
    private fun deleteWidgetId(id: Int) {
        val ids = (widgetPrefs.getStringSet(WIDGET_IDS, setOf()) ?: setOf<String?>()).toMutableSet()
        if (ids.remove(id.toString())) widgetPrefs.edit().putStringSet(WIDGET_IDS, ids).apply()
    }
}