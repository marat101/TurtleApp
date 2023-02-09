package com.turtleteam.turtleappcompose.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent

class ScheduleWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach {appWidgetId->
            UpdateWidget.Base(context,appWidgetId).fullUpdate()
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) return
        val id = intent?.extras?.getInt("id") ?: 0

        if (intent?.action == ActionsScheduleWidget.ACTION_REFRESH.actionName) {
            //TODO update bu press button
            UpdateWidget.Base(context,id).fullUpdate()
        }
        if (intent?.action == ActionsScheduleWidget.ACTION_NEXT_DAY.actionName) {
            WidgetDataManage.UpdateCurrentDay.Base(context).setNextDay()
            UpdateWidget.Base(context,id).fullUpdate()
        }
        if (intent?.action == ActionsScheduleWidget.ACTION_PREVIOUS_DAY.actionName) {
            WidgetDataManage.UpdateCurrentDay.Base(context).setPreviousDay()
            UpdateWidget.Base(context,id).fullUpdate()
        }
        super.onReceive(context, intent)
    }
}