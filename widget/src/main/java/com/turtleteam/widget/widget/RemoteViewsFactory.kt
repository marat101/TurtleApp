package com.turtleteam.widget.widget

import android.content.Context
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.turtleteam.widget.widget.model.WidgetScheduleState
import com.turtleteam.widget.widget.utils.WidgetDataManage

class RemoteViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {
    private val manageWidgetData = WidgetDataManage.Getters.Base(context)
    private var widgetData: WidgetScheduleState = WidgetScheduleState.ErrorScheduleNotSelected
    private var currentDay = 0
    private var isNightTheme = false
    override fun onDataSetChanged() {
        currentDay = manageWidgetData.getCurrentDayInt()
        widgetData = manageWidgetData.getCurrentSchedule()
        isNightTheme = manageWidgetData.isNightModeOn()
        Log.d("xdd", "onDataSetChanged: $currentDay")
    }


    override fun getViewAt(position: Int): RemoteViews {
        return widgetData.inflateRemoteView(
            packageName = context.packageName,
            position = position,
            currentDay = currentDay,
            isNightTheme = isNightTheme
        )
    }

    override fun getCount(): Int = widgetData.getCountItems(currentDay)
    override fun getViewTypeCount(): Int = 2
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun getLoadingView(): RemoteViews? = null
}