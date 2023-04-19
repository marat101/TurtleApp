package com.turtleteam.widget_schedule

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import androidx.work.*
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.widget_schedule.service.ScheduleViewService
import com.turtleteam.widget_schedule.widgetprovider.ScheduleWidgetProvider
import com.turtleteam.widget_schedule.widgetprovider.getPendingIntent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class MyWork(private val context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams), KoinComponent {
    override suspend fun doWork(): Result {
        val getSchedule: GetScheduleUC by inject(named("Groups"))

        updateWidget(
            AppWidgetManager.getInstance(context)
                .getAppWidgetIds(ComponentName(context, ScheduleWidgetProvider::class.java)),
            context,
            getSchedule.execute("ИС-23")
        )

        return Result.success()
    }

    fun updateWidget(ids: IntArray, context: Context, pairs: DaysList) {
        val manager = AppWidgetManager.getInstance(context)
        for (id in ids) {
            val service = Intent(context, ScheduleViewService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }

            val pairsRequest = OneTimeWorkRequestBuilder<MyWork>().build()

            WorkManager.getInstance(context).enqueue(pairsRequest)

            val rv = RemoteViews(context.packageName, R.layout.layout_widget).apply {
                setTextViewText(R.id.widget_date, pairs.days.first().day)
                setOnClickPendingIntent(
                    R.id.widget_refresh,
                    getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_REFRESH)
                )
                setRemoteAdapter(R.id.widget_listview, service)
            }
            manager.notifyAppWidgetViewDataChanged(id, R.id.widget_listview)
            manager.updateAppWidget(id, rv)
        }
    }
}