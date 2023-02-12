package com.turtleteam.widget.widget.utils

import android.appwidget.AppWidgetManager
import android.widget.RemoteViews

interface NotifyWidget {

    fun widgetWasUpdated(appWidgetId: Int, view: RemoteViews)
    fun listViewWasUpdated(appWidgetId: Int, listId: Int)

    class Base(
        private val appWidgetManager: AppWidgetManager
    ):NotifyWidget{
        override fun listViewWasUpdated(appWidgetId:Int,listId:Int) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, listId)
        }

        override fun widgetWasUpdated(appWidgetId:Int,view:RemoteViews) {
            appWidgetManager.updateAppWidget(appWidgetId,view)
        }
    }
}