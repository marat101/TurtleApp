package com.turtleteam.widget_schedule.widgetprovider

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import com.turtleteam.domain.model.widget.ScheduleWidgetState
import com.turtleteam.domain.repository.WidgetRepository
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.ScheduleProvider
import org.koin.core.component.KoinComponent

enum class PageAction {
    NEXT, PREVIOUS
}

interface WidgetUpdate {
    suspend fun fullUpdate(context: Context)

    fun pageChange(context: Context, page: PageAction?)
}

class WidgetUpdateImpl(private val widgetRepository: WidgetRepository, private val schedule: ScheduleProvider) : WidgetUpdate, KoinComponent {

    override suspend fun fullUpdate(context: Context) {
        val state = widgetRepository.getScheduleWidgetState()
        val manager = AppWidgetManager.getInstance(context)
        val ids = manager.getAppWidgetIds(ComponentName(context, ScheduleWidgetProvider::class.java))
        if (state == ScheduleWidgetState.empty() && state.schedule.name.isEmpty()) {
            emptyWidget(manager,ids, context)
            return
        }
        var mState = state

        mState = mState.copy(schedule = schedule.getSchedule(mState.schedule.name, mState.isGroup, context))

        val size = mState.schedule.days.size

        if (!mState.page.checkPage(size)) mState = mState.copy(page = 0)

        val day = if (mState.schedule.days.isEmpty()) "Empty" else mState.schedule.days[mState.page].day

        for (id in ids) {
            val rv = getRemoteViews(context, id, day, mState.schedule.name)
            widgetRepository.insertScheduleWidget(mState)
            manager.updateAppWidget(ids, rv)
            manager.notifyAppWidgetViewDataChanged(id, R.id.widget_listview)
        }
    }

    override fun pageChange(context: Context, page: PageAction?) {
        val state = widgetRepository.getScheduleWidgetState()
        if (state == ScheduleWidgetState.empty()) return

        var mState = state
        val manager = AppWidgetManager.getInstance(context)
        val ids =
            manager.getAppWidgetIds(ComponentName(context, ScheduleWidgetProvider::class.java))
        val size = mState.schedule.days.size

        if (page != null)
            when (page) {
                PageAction.NEXT -> {
                    val cPage = mState.page + 1
                    if (cPage.checkPage(size))
                        mState = mState.copy(
                            page = cPage
                        )

                }
                PageAction.PREVIOUS -> {
                    val cPage = mState.page - 1
                    if (cPage.checkPage(size))
                        mState = mState.copy(
                            page = cPage
                        )
                }
            }

        if (!mState.page.checkPage(size)) mState = mState.copy(page = 0)

        val day =
            if (mState.schedule.days.isEmpty()) "Empty" else mState.schedule.days[mState.page].day

        for (id in ids) {
            val rv = getRemoteViews(context, id, day, mState.schedule.name)
            widgetRepository.insertScheduleWidget(mState)
            manager.partiallyUpdateAppWidget(ids, rv)
            manager.notifyAppWidgetViewDataChanged(id, R.id.widget_listview)
        }
    }
}

fun Int.checkPage(size: Int): Boolean = this < size && this > -1