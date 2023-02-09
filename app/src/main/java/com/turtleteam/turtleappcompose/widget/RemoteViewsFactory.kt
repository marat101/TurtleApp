package com.turtleteam.turtleappcompose.widget

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.turtleteam.turtleappcompose.widget.model.WidgetScheduleState
import com.turtleteam.turtleappcompose.widget.utils.WidgetDataManage

class RemoteViewsFactory(
    private val context: Context,
) : RemoteViewsService.RemoteViewsFactory {

    private var widgetData: WidgetScheduleState = WidgetScheduleState.ErrorScheduleNotSelected
    private var currentDay = 0

    override fun onDataSetChanged() {
        WidgetDataManage.Getters.Base(context).also {
            currentDay = it.getCurrentDayInt()
            widgetData = it.getCurrentSchedule()
        }
        Log.d("xdd", "list view data was updated $widgetData")
    }


    override fun getViewAt(position: Int): RemoteViews =
        widgetData.inflateRemoteView(context.packageName, position, currentDay)

    override fun getCount(): Int = widgetData.getCountItems(currentDay)
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun getLoadingView(): RemoteViews? = null
}