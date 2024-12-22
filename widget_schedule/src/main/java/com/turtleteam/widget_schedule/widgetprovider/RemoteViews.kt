package com.turtleteam.widget_schedule.widgetprovider

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteCollectionItems
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.service.ScheduleViewService

fun getRemoteViews(context: Context?, id: Int, day: String, name: String? = null): RemoteViews {
    val service = Intent(context, ScheduleViewService::class.java).apply {
        // https://stackoverflow.com/questions/11350287/ongetviewfactory-only-called-once-for-multiple-widgets
        setData(Uri.fromParts("content", id.toString(), null))
    }
    val rv = RemoteViews(context!!.packageName, R.layout.layout_widget).apply {
        setTextViewText(R.id.widget_date, day)
        setOnClickPendingIntent(
            R.id.widget_refresh,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_REFRESH)
        )
        setOnClickPendingIntent(
            R.id.widget_button_next,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_NEXT)
        )
        setOnClickPendingIntent(
            R.id.widget_button_previous,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_PREVIOUS)
        )
        setOnClickPendingIntent(
            R.id.widget_select_schedule,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_SCHEDULE_SELECT)
        )
        name?.let {
            if (it.isNotBlank()) setTextViewText(R.id.widget_select_schedule, it)
        }
        setRemoteAdapter(R.id.widget_listview, service)
    }
    return rv
}

fun emptyWidget(id: Int, context: Context?): RemoteViews {
    println("MARAT emptyWidget $id")
    val service = Intent(context, ScheduleViewService::class.java).apply {
        // https://stackoverflow.com/questions/11350287/ongetviewfactory-only-called-once-for-multiple-widgets
        setData(Uri.fromParts("content", id.toString(), null))
    }
    val rv = RemoteViews(context!!.packageName, R.layout.layout_widget).apply {
        setOnClickPendingIntent(
            R.id.widget_refresh,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_SCHEDULE_SELECT)
        )
        setOnClickPendingIntent(
            R.id.widget_button_next,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_SCHEDULE_SELECT)
        )
        setOnClickPendingIntent(
            R.id.widget_button_previous,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_SCHEDULE_SELECT)
        )
        setOnClickPendingIntent(
            R.id.widget_select_schedule,
            getPendingIntent(context, id, ScheduleWidgetProvider.CLICK_ON_SCHEDULE_SELECT)
        )
        setRemoteAdapter(R.id.widget_listview, service)
    }
    return rv
}