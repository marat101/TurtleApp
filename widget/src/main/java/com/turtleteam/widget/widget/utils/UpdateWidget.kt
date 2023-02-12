package com.turtleteam.widget.widget.utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.turtleteam.widget.widget.ScheduleWidgetService

interface UpdateWidget {

    fun setListViewAdapter(view: RemoteViews, @IdRes listId: Int)
    fun setText(view: RemoteViews, @IdRes viewId: Int, text: String, @ColorInt color: Int)
    fun setBackground(view: RemoteViews, @IdRes viewId: Int, @DrawableRes background: Int)
    fun setOnClickListener(view: RemoteViews, @IdRes viewId: Int, onClickIntent: PendingIntent): RemoteViews

    class Base(
        private val context: Context,
        private val appWidgetId: Int,
    ) : UpdateWidget {

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

        override fun setOnClickListener(
            view: RemoteViews,
            viewId: Int,
            onClickIntent: PendingIntent,
        ): RemoteViews {
            view.setOnClickPendingIntent(viewId, onClickIntent)
            return view
        }
    }
}