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
            val int = Intent(context, widgetProvider).apply {
                this.action = action.actionName
                putExtra("id", appWidgetId)
            }

            return PendingIntent.getBroadcast(
                context,
                0,
                int,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        override fun createPendingOpenActivity(activity:Class<*>): PendingIntent {
            val intent = Intent(context,activity ).apply {
                putExtra("id", appWidgetId)
            }
            return PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
    }
}