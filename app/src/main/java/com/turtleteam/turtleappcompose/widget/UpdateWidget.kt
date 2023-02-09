package com.turtleteam.turtleappcompose.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.turtleteam.turtleappcompose.R
import com.turtleteam.turtleappcompose.WidgetActivity

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
                    StackWidgetService::class.java//TODO как передать йобаный класс в параметры функции
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