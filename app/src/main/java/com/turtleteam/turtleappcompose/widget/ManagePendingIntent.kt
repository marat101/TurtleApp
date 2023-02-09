package com.turtleteam.turtleappcompose.widget

import android.app.PendingIntent
import android.content.Context
import android.content.Intent

interface ManagePendingIntent {
    fun createPendingBroadcastIntent(action: ActionsScheduleWidget): PendingIntent

    class Base(private val appWidgetId: Int, private val context: Context) : ManagePendingIntent {
        override fun createPendingBroadcastIntent(action: ActionsScheduleWidget): PendingIntent {
            return PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, ScheduleWidget::class.java).apply {
                    this.action = action.actionName
                    putExtra("id", appWidgetId)
                },
                0
            )
        }

    }
}