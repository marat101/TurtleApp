package com.turtleteam.widget_schedule.widgetprovider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import com.turtleteam.domain.repository.WidgetRepository
import com.turtleteam.widget_schedule.schedule_select.ScheduleSelectActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

class ScheduleWidgetProvider : AppWidgetProvider(), CoroutineScope, KoinComponent {

    private val widgetRep: WidgetRepository by inject()
    private val update: WidgetUpdate by inject()

    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"

        // Клики по кнопкам
        const val CLICK_ON_REFRESH = "clickRefresh"
        const val CLICK_ON_NEXT = "next"
        const val CLICK_ON_PREVIOUS = "previous"
        const val CLICK_ON_SCHEDULE_SELECT = "schedule"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            CLICK_ON_REFRESH -> {
                this.launch {
                    update.fullUpdate(context!!)
                }
            }
            CLICK_ON_PREVIOUS -> {
                this.launch {
                    update.pageChange(context!!, PageAction.PREVIOUS)
                }
            }
            CLICK_ON_NEXT -> {
                this.launch {
                    update.pageChange(context!!, PageAction.NEXT)
                }
            }
            CLICK_ON_SCHEDULE_SELECT -> {
                this.launch {
                    val mIntent = Intent(context, ScheduleSelectActivity::class.java)
                    mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context?.startActivity(mIntent)
                }
            }
        }
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        this.launch {
            update.fullUpdate(context!!)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        this.launch {
            widgetRep.deleteScheduleWidget()
        }
        super.onDeleted(context, appWidgetIds)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
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