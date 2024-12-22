package com.turtleteam.widget_schedule.widgetprovider

import android.appwidget.AppWidgetManager
import android.content.Context
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.WidgetRepository
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.ScheduleProvider
import com.turtleteam.widget_schedule.schedule_select.SelectType
import com.turtleteam.widget_schedule.utils.isGroup
import org.koin.core.component.KoinComponent

enum class PageAction {
    NEXT, PREVIOUS
}

interface WidgetUpdate {
    suspend fun upsertWidget(name: String, type: SelectType, widgetId: Int, context: Context)

    suspend fun updateSchedule(widgetId: Int, context: Context)

    fun reloadAllWidgets(context: Context)

    fun changePage(widgetId: Int, context: Context, page: PageAction?)

    fun deleteWidget(widgetId: Int)
}

class WidgetUpdateImpl(
    private val widgetRepository: WidgetRepository,
    private val scheduleProvider: ScheduleProvider
) : WidgetUpdate, KoinComponent {

    override suspend fun upsertWidget(
        name: String,
        type: SelectType,
        widgetId: Int,
        context: Context
    ) {
        widgetRepository.upsertWidget(
            widgetId,
            ScheduleWidgetState(DaysList(emptyList(), name), 0, type.isGroup())
        )
        updateSchedule(widgetId, context)
    }

    override suspend fun updateSchedule(widgetId: Int, context: Context) {
        var state = widgetRepository.getWidgetStateById(widgetId)
        val manager = AppWidgetManager.getInstance(context)

        if (state == null) {
            val rv = emptyWidget(widgetId, context)
            manager.updateAppWidget(widgetId, rv)
            manager.notifyAppWidgetViewDataChanged(widgetId, R.id.widget_listview)
            return
        }

        val schedule = scheduleProvider.getSchedule(state.schedule.name, state.isGroup)
        state = state.copy(schedule = schedule)
        val size = state.schedule.days.size

        if (!state.page.checkPage(size)) state = state.copy(page = 0)

        val day =
            if (state.schedule.days.isEmpty()) "Empty" else state.schedule.days[state.page].day

        val rv = getRemoteViews(context, widgetId, day, state.schedule.name)
        rv.setScrollPosition(R.id.widget_listview, 0)
        widgetRepository.upsertWidget(widgetId, state)
        manager.updateAppWidget(widgetId, rv)
        manager.notifyAppWidgetViewDataChanged(widgetId, R.id.widget_listview)
    }

    override fun reloadAllWidgets(context: Context) {
        val widgetsStates = widgetRepository.getAllWidgetsIds()
            .map { Pair(it, widgetRepository.getWidgetStateById(it)) }
        val manager = AppWidgetManager.getInstance(context)
        widgetsStates.forEach { (widgetId, state) ->
            val rv = if (state == null) {
                emptyWidget(widgetId, context)
            } else {
                getRemoteViews(
                    context,
                    widgetId,
                    state.schedule.days[state.page].day,
                    state.schedule.name
                ).apply {
                    setScrollPosition(R.id.widget_listview, 0)
                }
            }
            manager.updateAppWidget(widgetId, rv)
            manager.notifyAppWidgetViewDataChanged(widgetId, R.id.widget_listview)
        }
    }

    override fun changePage(widgetId: Int, context: Context, page: PageAction?) {
        var state = widgetRepository.getWidgetStateById(widgetId) ?: return
        val manager = AppWidgetManager.getInstance(context)
        val size = state.schedule.days.size

        if (page != null)
            when (page) {
                PageAction.NEXT -> {
                    val cPage = state.page + 1
                    if (cPage.checkPage(size))
                        state = state.copy(
                            page = cPage
                        )

                }

                PageAction.PREVIOUS -> {
                    val cPage = state.page - 1
                    if (cPage.checkPage(size))
                        state = state.copy(
                            page = cPage
                        )
                }
            }

        if (!state.page.checkPage(size)) state = state.copy(page = 0)

        val day =
            if (state.schedule.days.isEmpty()) "Empty" else state.schedule.days[state.page].day

        val rv = getRemoteViews(context, widgetId, day, state.schedule.name)
        rv.setScrollPosition(R.id.widget_listview, 0)
        widgetRepository.upsertWidget(widgetId, state)
        manager.partiallyUpdateAppWidget(widgetId, rv)
        manager.notifyAppWidgetViewDataChanged(widgetId, R.id.widget_listview)
    }

    override fun deleteWidget(widgetId: Int) {
        widgetRepository.deleteWidgetById(widgetId)
    }
}

fun Int.checkPage(size: Int): Boolean = this < size && this > -1