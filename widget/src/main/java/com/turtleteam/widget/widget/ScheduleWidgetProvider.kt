package com.turtleteam.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import com.turtleteam.widget.R
import com.turtleteam.widget.widget.model.ActionsScheduleWidget
import com.turtleteam.widget.widget.utils.*

class ScheduleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach { appWidgetId ->
            updateScheduleWidget(context, appWidgetId,appWidgetManager)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.extras?.getInt("id") ?: 0

        if (intent.action == ActionsScheduleWidget.ACTION_REFRESH.actionName) {
            Log.d("xdd", "onReceive: ACTION_REFRESH")
            //TODO update bu press button
            updateScheduleWidget(context, id,AppWidgetManager.getInstance(context))
        }
        if (intent.action == ActionsScheduleWidget.ACTION_NEXT_DAY.actionName) {
            Log.d("xdd", "onReceive: ACTION_NEXT_DAY")
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setNextDay())
                updateScheduleWidget(context, id,AppWidgetManager.getInstance(context))
        }
        if (intent.action == ActionsScheduleWidget.ACTION_PREVIOUS_DAY.actionName) {
            Log.d("xdd", "onReceive: ACTION_PREVIOUS_DAY")
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setPreviousDay())
                updateScheduleWidget(context, id, AppWidgetManager.getInstance(context))
        }
        super.onReceive(context, intent)
    }
}

fun updateScheduleWidget(context: Context, widgetId: Int,appWidgetManager: AppWidgetManager) {
    Log.d("xdd", "updateScheduleWidget: start")
    val view = RemoteViews(context.packageName, R.layout.schedule_widget)
    val inflater = InflateWidget.Base(
        view,
        updateWidget = UpdateWidget.Base(context, widgetId),
        managePendingIntent = ManagePendingIntent.Base(context, widgetId),
        context = context,
        dataManage = WidgetDataManage.Getters.Base(context)
    )
    inflater.inflateBtnPrev()
    inflater.inflateBtnNext()
    inflater.inflateBtnRefresh()
    inflater.inflateBtnCurrentGroup()
    inflater.inflateCurrentDay()
    inflater.inflateDayCounter()
    inflater.inflateLiseView()
    inflater.inflateRootBackground()
    val notifier = NotifyWidget.Base(appWidgetManager)
    notifier.listViewWasUpdated(widgetId, R.id.listView)
    notifier.widgetWasUpdated(widgetId, inflater.get())
    Log.d("xdd", "updateScheduleWidget: end")

}
