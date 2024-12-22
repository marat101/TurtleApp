package com.turtleteam.widget_schedule.widgetprovider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import com.turtleteam.widget_schedule.schedule_select.ScheduleSelectActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ScheduleWidgetProvider : AppWidgetProvider(), CoroutineScope, KoinComponent {

    private val widgetUpdate: WidgetUpdate by inject()

    companion object {
        // Клики по кнопкам
        const val CLICK_ON_REFRESH = "com.android.turtleapp.clickRefresh"
        const val CLICK_ON_NEXT = "com.android.turtleapp.next"
        const val CLICK_ON_PREVIOUS = "com.android.turtleapp.previous"
        const val CLICK_ON_SCHEDULE_SELECT = "com.android.turtleapp.schedule"
    }

    override fun onEnabled(context: Context?) {
        if (context != null) widgetUpdate.reloadAllWidgets(context)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val widgetId =
            intent?.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        if (intent?.action == CLICK_ON_SCHEDULE_SELECT) {
            val mIntent = Intent(context, ScheduleSelectActivity::class.java)
            mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId)
            context?.startActivity(mIntent)
        }

        if (widgetId != Int.MAX_VALUE)
            when (intent?.action) {
                CLICK_ON_REFRESH -> {
                    launch {
                        widgetUpdate.updateSchedule(widgetId, context!!)
                    }
                }

                CLICK_ON_PREVIOUS -> {
                    widgetUpdate.changePage(widgetId, context!!, PageAction.PREVIOUS)
                }

                CLICK_ON_NEXT -> {
                    widgetUpdate.changePage(widgetId, context!!, PageAction.NEXT)
                }
            }
    }

    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        if (context != null) {
            launch {
                appWidgetIds?.forEach {
                    widgetUpdate.updateSchedule(it, context)
                }
            }
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        appWidgetIds?.forEach {
            widgetUpdate.deleteWidget(it)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}

fun getPendingIntent(context: Context, id: Int, action: String): PendingIntent? {
    val clickIntent = Intent(context, ScheduleWidgetProvider::class.java)
    clickIntent.action = action
    clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id)
    return PendingIntent.getBroadcast(
        context,
        id,
        clickIntent,
        PendingIntent.FLAG_IMMUTABLE
    )
}