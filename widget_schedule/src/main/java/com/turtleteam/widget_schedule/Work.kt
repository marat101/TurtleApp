package com.turtleteam.widget_schedule

import android.appwidget.AppWidgetManager
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.turtleteam.widget_schedule.utils.toSelectType
import com.turtleteam.widget_schedule.widgetprovider.WidgetUpdate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateScheduleWork(private val context: Context, private val workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), KoinComponent {

    companion object {
        const val SCHEDULE_NAME = "schedule"
        const val SCHEDULE_TYPE = "isgroup"
    }

    override suspend fun doWork(): Result {
        val update: WidgetUpdate by inject()
        val name = workerParams.inputData.getString(SCHEDULE_NAME)
        val type = workerParams.inputData.getString(SCHEDULE_TYPE)?.toSelectType()
        val id = workerParams.inputData.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, Int.MAX_VALUE)
        if (name != null && type != null)
            update.upsertWidget(name, type,id, context)
        return Result.success()
    }
}