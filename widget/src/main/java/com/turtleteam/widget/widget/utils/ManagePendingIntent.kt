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
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//                PendingIntent.FLAG_IMMUTABLE
//                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                //PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        }

        override fun createPendingOpenActivity(activity:Class<*>): PendingIntent {
            return PendingIntent.getActivity(
                context,
                0,
                Intent(context,activity ).apply {
                    putExtra("id", appWidgetId)
                },
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )
        }
    }
}