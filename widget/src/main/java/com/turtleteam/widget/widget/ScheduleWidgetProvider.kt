package com.turtleteam.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import com.turtleteam.widget.widget.model.ActionsScheduleWidget
import com.turtleteam.widget.widget.utils.UpdateWidget
import com.turtleteam.widget.widget.utils.WidgetDataManage

class ScheduleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach {appWidgetId->
            UpdateWidget.Base(context,appWidgetId).fullUpdate()
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val id = intent.extras?.getInt("id") ?: 0

        if (intent.action == ActionsScheduleWidget.ACTION_REFRESH.actionName) {
            //TODO update bu press button
            UpdateWidget.Base(context,id).fullUpdate()
        }
        if (intent.action == ActionsScheduleWidget.ACTION_NEXT_DAY.actionName) {
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setNextDay())
                UpdateWidget.Base(context,id).fullUpdate()
        }
        if (intent.action == ActionsScheduleWidget.ACTION_PREVIOUS_DAY.actionName) {
            if(WidgetDataManage.UpdateCurrentDay.Base(context).setPreviousDay())
                UpdateWidget.Base(context,id).fullUpdate()
        }
        super.onReceive(context, intent)
    }
}
