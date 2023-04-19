package com.turtleteam.widget_schedule.widgetprovider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import androidx.work.*
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.widget_schedule.MyWork
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import java.util.concurrent.TimeUnit

class ScheduleWidgetProvider : AppWidgetProvider(), KoinComponent {

    //    private val teachersSchedule: GetTeacherScheduleUseCase by inject()
//    private val saveWidget: SaveScheduleWidget by inject()
//    private val getWidget: GetScheduleWidget by inject()
    private val getSchedule: GetScheduleUC by inject(named("Groups"))

    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"

        // Клики по кнопкам
        const val CLICK_ON_REFRESH = "clickRefresh"
        private const val CLICK_ON_NEXT = "next"
        private const val CLICK_ON_PREVIOUS = "previous"
        private const val CLICK_ON_SCHEDULE_SELECT = "schedule"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            CLICK_ON_REFRESH -> {
                return
            }
            ACTION_APPWIDGET_UPDATE -> {
                onUpdate(context)
            }
        }
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
//        val work = OneTimeWorkRequestBuilder<MyWork>().build()
//
//        WorkManager.getInstance(context!!).enqueueUniqueWork("updateWidget", ExistingWorkPolicy.REPLACE, work)

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onEnabled(context: Context?) {
        val work = PeriodicWorkRequestBuilder<MyWork>(1L, TimeUnit.MINUTES).build()

        WorkManager.getInstance(context!!)
            .enqueueUniquePeriodicWork("updateWidget", ExistingPeriodicWorkPolicy.KEEP, work)
    }

    override fun onDisabled(context: Context?) {
        WorkManager.getInstance(context!!).cancelUniqueWork("updateWidget")
    }
}

fun getPendingIntent(context: Context, id: Int, action: String): PendingIntent? {
    val clickIntent = Intent(context, ScheduleWidgetProvider::class.java)
    clickIntent.action = action
    clickIntent.putExtra("widgetId", id)
    return PendingIntent.getBroadcast(
        context,
        0,
        clickIntent,
        PendingIntent.FLAG_IMMUTABLE
    )
}