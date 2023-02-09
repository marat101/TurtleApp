package com.turtleteam.turtleappcompose.widget.utils

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.turtleteam.turtleappcompose.R
import com.turtleteam.turtleappcompose.WidgetActivity
import com.turtleteam.turtleappcompose.widget.WidgetService
import com.turtleteam.turtleappcompose.widget.model.ActionsScheduleWidget

interface UpdateWidget {

    fun setListViewAdapter(view: RemoteViews, @IdRes listId: Int)
    fun setText(view: RemoteViews, @IdRes viewId: Int, text: String)

    fun notifyWidgetUpdated(view: RemoteViews, appWidgetManager: AppWidgetManager)
    fun notifyListUpdated(appWidgetManager: AppWidgetManager, @IdRes listId: Int)

    fun fullUpdate()
    class Base(
        private val context: Context,
        private val appWidgetId: Int,
    ) : UpdateWidget {
        override fun fullUpdate() {
            val manage = WidgetDataManage.Getters.Base(context)
            val view = RemoteViews(context.packageName, R.layout.schedule_widget)
            setListViewAdapter(view, R.id.listView)
            setText(view, R.id.widgetCurrentGroup, manage.getCurrentGroupName())
            setText(view, R.id.widgetDay, manage.getCurrentDayString())
            val isNightMode = manage.isNightModeOn()
            view.setTextColor(
                R.id.widgetDay,
                context.getColor(
                    if (isNightMode) R.color.widgetTextTitleColor_NIGHT
                    else R.color.widgetTextTitleColor_DAY
                )
            )
            setText(view, R.id.widgetDayCount, manage.getDaysCount())
            view.setInt(
                R.id.widgetRoot,
                "setBackgroundResource",
                if (isNightMode) R.drawable.block_corners_night
                else R.drawable.block_corners
            )
            view.setOnClickPendingIntent(
                R.id.widgetCurrentGroup,
                PendingIntent.getActivity(
                    context,
                    0,
                    Intent(context, WidgetActivity::class.java).apply {
                        putExtra("id", appWidgetId)
                    },
                    0
                )
            )

            val manageBroadcastIntent = ManagePendingIntent.Base(appWidgetId, context)
            view.setOnClickPendingIntent(
                R.id.btnRefresh,
                manageBroadcastIntent.createPendingBroadcastIntent(ActionsScheduleWidget.ACTION_REFRESH)
            )
            view.setOnClickPendingIntent(
                R.id.btnNext,
                manageBroadcastIntent.createPendingBroadcastIntent(ActionsScheduleWidget.ACTION_NEXT_DAY)
            )
            view.setOnClickPendingIntent(
                R.id.btnPrev,
                manageBroadcastIntent.createPendingBroadcastIntent(ActionsScheduleWidget.ACTION_PREVIOUS_DAY)
            )
            AppWidgetManager.getInstance(context).also {
                notifyWidgetUpdated(view, it)
                notifyListUpdated(it, R.id.listView)
            }
        }

        override fun setListViewAdapter(
            view: RemoteViews,
            @IdRes listId: Int,
            //java: Class<out AppWidgetProvider>,//TODO
        ) {
            view.setRemoteAdapter(
                listId, Intent(
                    context,
                    WidgetService::class.java//TODO
                ).apply {
                    putExtra("id", appWidgetId)
                    data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
                }
            )
        }

        override fun setText(view: RemoteViews, @IdRes viewId: Int, text: String) {
            view.setCharSequence(viewId, "setText", text)
        }

        override fun notifyListUpdated(appWidgetManager: AppWidgetManager, @IdRes listId: Int) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, listId)
        }

        override fun notifyWidgetUpdated(view: RemoteViews, appWidgetManager: AppWidgetManager) {
            appWidgetManager.updateAppWidget(appWidgetId, view)
        }
    }
}