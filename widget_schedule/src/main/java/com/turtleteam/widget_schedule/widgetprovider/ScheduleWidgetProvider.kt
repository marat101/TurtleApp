package com.turtleteam.widget_schedule.widgetprovider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.teachers.GetTeacherScheduleUseCase
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.service.ScheduleViewService
import com.turtleteam.widget_schedule.utils.ScheduleFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScheduleWidgetProvider: AppWidgetProvider(), KoinComponent {

    private val teachersSchedule: GetTeacherScheduleUseCase by inject()
    private val localTeachersSchedule: GetTeacherScheduleUseCase by inject()

    private val groupsSchedule: GetTeacherScheduleUseCase by inject()
    private val localGroupssSchedule: GetTeacherScheduleUseCase by inject()


    companion object {
        const val EXTRA_ITEM = "com.turtleteam.widget_schedule.widgetprovider.EXTRA_ITEM"

        // Клики по кнопкам
        const val CLICK_ON_REFRESH = "clickRefresh"
        private const val CLICK_ON_NEXT = "next"
        private const val CLICK_ON_PREVIOUS = "previous"
        private const val CLICK_ON_SCHEDULE_SELECT = "schedule"

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val componentName = ComponentName(context!!, ScheduleWidgetProvider::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            for (i in appWidgetIds!!) {
                val intent = Intent(context, ScheduleViewService::class.java)
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, i)
                intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
                val rv = RemoteViews(context.packageName, R.layout.layout_widget)
                rv.setRemoteAdapter(
                    R.id.widget_listview,
                    intent)

                rv.setTextViewText(R.id.widget_select_schedule, "ИС-23")
                val schedule = groupsSchedule.execute("ИС-23")

                if (schedule is States.Success)
                ScheduleViewService.pairs = ScheduleFormatter.getFormattedList(schedule.value.days[0])

                rv.setOnClickPendingIntent(R.id.widget_refresh,
                    getPendingIntent(context, appWidgetIds, CLICK_ON_REFRESH))
                rv.setOnClickPendingIntent(R.id.widget_button_next,
                    getPendingIntent(context, appWidgetIds, CLICK_ON_NEXT))
                rv.setOnClickPendingIntent(R.id.widget_button_previous,
                    getPendingIntent(context, appWidgetIds, CLICK_ON_PREVIOUS))
                rv.setOnClickPendingIntent(R.id.widget_select_schedule,
                    getPendingIntent(context, appWidgetIds, CLICK_ON_SCHEDULE_SELECT))

                appWidgetManager?.notifyAppWidgetViewDataChanged(i, R.id.widget_listview)
                appWidgetManager?.updateAppWidget(componentName, null)
                appWidgetManager?.updateAppWidget(componentName, rv)
            }
        }
    }

    fun getPendingIntent(context: Context, idS: IntArray, action: String): PendingIntent? {
        val clickIntent = Intent(context, ScheduleWidgetProvider::class.java)
        clickIntent.action = action
        clickIntent.putExtra("widgetIds", idS)
        return PendingIntent.getBroadcast(context,
            0,
            clickIntent,
            PendingIntent.FLAG_IMMUTABLE)
    }
}