package com.turtleteam.widget.widget.utils

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import com.turtleteam.widget.widget.ScheduleWidgetProvider

interface NotifyWidget {

    fun widgetWasUpdated( view: RemoteViews)
    fun listViewWasUpdated(listId: Int)

    class Base(
        private val appWidgetManager: AppWidgetManager,
        private val appWidgetId: Int,
        private val context: Context,
    ) : NotifyWidget {
        override fun listViewWasUpdated(listId: Int) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, listId)
        }

        override fun widgetWasUpdated( view: RemoteViews) {
            appWidgetManager.updateAppWidget(
                ComponentName(
                    context,
                    ScheduleWidgetProvider::class.java
                ),
                view
            )
        }
    }

}