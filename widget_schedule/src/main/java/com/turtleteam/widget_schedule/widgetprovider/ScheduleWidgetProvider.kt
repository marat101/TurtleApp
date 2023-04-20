package com.turtleteam.widget_schedule.widgetprovider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.service.ScheduleViewService
import com.turtleteam.widget_schedule.utils.ScheduleFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import kotlin.coroutines.CoroutineContext

class ScheduleWidgetProvider : AppWidgetProvider(), CoroutineScope, KoinComponent {

    //    private val teachersSchedule: GetTeacherScheduleUseCase by inject()
//    private val saveWidget: SaveScheduleWidget by inject()
//    private val getWidget: GetScheduleWidget by inject()
    private val getSchedule: GetScheduleUC by inject(named("Groups"))
    private val json: Json by inject()

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
                this.launch {
                    val pairs = getSchedule.execute("ИС-23")
                    updateWidget(
                        AppWidgetManager.getInstance(context!!)
                            .getAppWidgetIds(ComponentName(context, this::class.java)),
                        context, pairs
                    )
                }
                return
            }
            CLICK_ON_PREVIOUS -> {
                this.launch {
                    val pairs = getSchedule.execute("ИС-23")
                    partiallyUpdateWidget(
                        AppWidgetManager.getInstance(context!!)
                            .getAppWidgetIds(ComponentName(context, this::class.java)),
                        context, pairs,
                        0
                    )
                }
            }
            CLICK_ON_NEXT -> {
                this.launch {
                    val pairs = getSchedule.execute("ИС-23")
                    partiallyUpdateWidget(
                        AppWidgetManager.getInstance(context!!)
                            .getAppWidgetIds(ComponentName(context, this::class.java)),
                        context, pairs,
                        1
                    )
                }
            }
            else -> super.onReceive(context, intent)
        }
    }

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        this.launch {
            val pairs = getSchedule.execute("ИС-23")
            updateWidget(appWidgetIds!!, context!!, pairs)
        }
    }

    private fun partiallyUpdateWidget(ids: IntArray, context: Context, pairs: DaysList, num: Int) {
        val manager = AppWidgetManager.getInstance(context)
        for (id in ids) {
            val service = Intent(context, ScheduleViewService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id)
                putExtra(ScheduleViewService.EXTRA_DAY, num)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }

            val rv = RemoteViews(context.packageName, R.layout.layout_widget).apply {
                setTextViewText(R.id.widget_date, pairs.days[num].day)
                setOnClickPendingIntent(
                    R.id.widget_refresh,
                    getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_REFRESH)
                )
                setRemoteAdapter(R.id.widget_listview, service)
            }
            manager.notifyAppWidgetViewDataChanged(id, R.id.widget_listview)
            manager.updateAppWidget(ids, rv)
        }
    }

    private fun updateWidget(ids: IntArray, context: Context, pairs: DaysList) {
        val manager = AppWidgetManager.getInstance(context)
        for (id in ids) {
            val service = Intent(context, ScheduleViewService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, id)
                val pairsStr =
                    json.encodeToString(ScheduleFormatter.getFormattedList(pairs.days[0]))
                putExtra(ScheduleViewService.PAIRS_EXTRA, pairsStr)
                putExtra(ScheduleViewService.EXTRA_DAY, 0)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }

            val rv = RemoteViews(context.packageName, R.layout.layout_widget).apply {
                setTextViewText(R.id.widget_date, pairs.days.first().day)
                setOnClickPendingIntent(
                    R.id.widget_refresh,
                    getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_REFRESH)
                )
                setOnClickPendingIntent(
                    R.id.widget_button_next,
                    getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_PREVIOUS)
                )
                setOnClickPendingIntent(
                    R.id.widget_button_previous,
                    getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_PREVIOUS)
                )
                setRemoteAdapter(R.id.widget_listview, service)
            }
            manager.notifyAppWidgetViewDataChanged(id, R.id.widget_listview)
            manager.updateAppWidget(id, rv)
        }
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