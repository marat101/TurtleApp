package com.turtleteam.widget_schedule.service

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.widget.RemoteViewsService
import org.koin.core.component.KoinComponent

class ScheduleViewService : RemoteViewsService(), KoinComponent {

    companion object {
        const val TIME_ICON = "⏳"
        const val DOCTRINE_ICON = "\uD83D\uDCD6"
        const val TEACHER_ICON = "\uD83C\uDF93"
        const val AUDITORIA_ICON = "\uD83D\uDD11"
        const val CORPUS_ICON = "\uD83C\uDFE2"
    }

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        val widgetId = intent?.data?.schemeSpecificPart?.toInt() ?: AppWidgetManager.INVALID_APPWIDGET_ID
        return ScheduleViewsFactory(applicationContext, widgetId)
    }
}