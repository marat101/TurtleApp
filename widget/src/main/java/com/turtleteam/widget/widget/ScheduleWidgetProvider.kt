package com.turtleteam.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import com.turtleteam.widget.R
import com.turtleteam.widget.widget.model.ActionsScheduleWidget
import com.turtleteam.widget.widget.utils.InflateWidget
import com.turtleteam.widget.widget.utils.NotifyWidget
import com.turtleteam.widget.widget.utils.WidgetDataManage

class ScheduleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds.forEach { appWidgetId ->
            updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(appWidgetManager,appWidgetId, context)
            )
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val appWidgetId = intent.extras?.getInt("id") ?: 0
        Log.d("xdd", "onReceive: $appWidgetId")
        if (intent.action == ActionsScheduleWidget.ACTION_REFRESH.actionName) {
            Log.d("xdd", "onReceive: ACTION_REFRESH")
            //TODO update bu press button
            updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(AppWidgetManager.getInstance(context),appWidgetId, context)
            )
        }
        if (intent.action == ActionsScheduleWidget.ACTION_NEXT_DAY.actionName) {
            Log.d("xdd", "onReceive: ACTION_NEXT_DAY")
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setNextDay())
                updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(AppWidgetManager.getInstance(context),appWidgetId, context)
            )
        }
        if (intent.action == ActionsScheduleWidget.ACTION_PREVIOUS_DAY.actionName) {
            Log.d("xdd", "onReceive: ACTION_PREVIOUS_DAY")
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setPreviousDay())
                updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(AppWidgetManager.getInstance(context),appWidgetId, context)
            )
        }
        super.onReceive(context, intent)
    }
}

fun updateScheduleWidget(inflater:InflateWidget,notifier:NotifyWidget) {
    inflater.inflateBtnPrev()
    inflater.inflateBtnNext()
    inflater.inflateBtnRefresh()
    inflater.inflateBtnCurrentGroup()
    inflater.inflateCurrentDay()
    inflater.inflateDayCounter()
    inflater.inflateLiseView()
    inflater.inflateRootBackground()
    notifier.listViewWasUpdated(R.id.listView)
    notifier.widgetWasUpdated(inflater.getView())
}
//fun updateScheduleWidget(context: Context, widgetId: Int, appWidgetManager: AppWidgetManager) {
//    Log.d("xdd", "updateScheduleWidget: start widgetId $widgetId")
//    val inflater:InflateWidget = InflateWidget.Base(
//        widgetLayoutId = R.layout.schedule_widget,
//        context = context,
//        widgetId = widgetId
//    )
//    inflater.inflateBtnPrev()
//    inflater.inflateBtnNext()
//    inflater.inflateBtnRefresh()
//    inflater.inflateBtnCurrentGroup()
//    inflater.inflateCurrentDay()
//    inflater.inflateDayCounter()
//    inflater.inflateLiseView()
//    inflater.inflateRootBackground()
//    val notifier = NotifyWidget.Base(appWidgetManager, widgetId)
//    notifier.listViewWasUpdated(R.id.listView)
//    notifier.widgetWasUpdated(context, inflater.getView())
//
//}
