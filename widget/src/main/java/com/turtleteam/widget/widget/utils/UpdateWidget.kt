package com.turtleteam.widget.widget.utils

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.RemoteViews
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.turtleteam.widget.R
import com.turtleteam.widget.WidgetActivity
import com.turtleteam.widget.widget.ScheduleWidgetService
import com.turtleteam.widget.widget.model.ActionsScheduleWidget

interface UpdateWidget {

    fun setListViewAdapter(view: RemoteViews, @IdRes listId: Int)
    fun setText(view: RemoteViews, @IdRes viewId: Int, text: String, @ColorInt color: Int)
    fun setBackground(view: RemoteViews, @IdRes viewId: Int, @DrawableRes background: Int)
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
            val isNightMode = manage.isNightModeOn()
            setListViewAdapter(view, R.id.listView)
            setText(
                view,
                R.id.widgetCurrentGroup,
                manage.getCurrentGroupName(),
                context.getColor(
                    if (isNightMode) R.color.widgetTextTitleColor_NIGHT
                    else R.color.widgetTextTitleColor_DAY
                )
            )
            setText(
                view,
                R.id.widgetDay,
                manage.getCurrentDayString(),
                context.getColor(
                    if (isNightMode) R.color.widgetTextTitleColor_NIGHT
                    else R.color.widgetTextTitleColor_DAY
                )
            )
            setText(
                view,
                R.id.widgetDayCount,
                manage.getDaysCount(),
                Color.GRAY
            )
            setBackground(
                view,
                R.id.widgetRoot,
                if (isNightMode) R.drawable.block_corners_night
                else R.drawable.block_corners
            )
            setBackground(
                view,
                R.id.widgetCurrentGroup,
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
                    ScheduleWidgetService::class.java//TODO
                ).apply {
                    putExtra("id", appWidgetId)
                    data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
                }
            )
        }

        override fun setText(
            view: RemoteViews,
            @IdRes viewId: Int,
            text: String,
            @ColorInt color: Int,
        ) {
            view.setCharSequence(viewId, "setText", text)
            view.setTextColor(viewId, color)
        }

        override fun setBackground(
            view: RemoteViews,
            @IdRes viewId: Int,
            @DrawableRes background: Int,
        ) {
            view.setInt(viewId, "setBackgroundResource", background)
        }

        override fun notifyListUpdated(appWidgetManager: AppWidgetManager, @IdRes listId: Int) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, listId)
        }

        override fun notifyWidgetUpdated(view: RemoteViews, appWidgetManager: AppWidgetManager) {
            appWidgetManager.updateAppWidget(appWidgetId, view)
        }
    }
}