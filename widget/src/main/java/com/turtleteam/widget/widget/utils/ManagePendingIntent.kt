package com.turtleteam.widget.widget.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.turtleteam.widget.widget.model.ActionsScheduleWidget

interface ManagePendingIntent {
    fun createPendingBroadcastIntent(action: ActionsScheduleWidget,widgetProvider:Class<*>): PendingIntent
    fun createPendingOpenActivity(activity:Class<*>):PendingIntent

    class Base(
        private val context: Context,
        private val appWidgetId: Int
    ) : ManagePendingIntent {
        override fun createPendingBroadcastIntent(
            action: ActionsScheduleWidget,
            widgetProvider: Class<*>
        ): PendingIntent {
            return PendingIntent.getBroadcast(
                context,
                0,
                Intent(context, widgetProvider).apply {
                    this.action = action.actionName
                    putExtra("id", appWidgetId)
                },
                0
            )
        }

        override fun createPendingOpenActivity(activity:Class<*>): PendingIntent {
            return PendingIntent.getActivity(
                context,
                0,
                Intent(context,activity ).apply {
                    putExtra("id", appWidgetId)
                },
                0
            )
        }
    }
}