package com.turtleteam.widget.widget

import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.turtleteam.widget.widget.model.WidgetScheduleState
import com.turtleteam.widget.widget.utils.WidgetDataManage

class RemoteViewsFactory(
    private val manageWidgetData: WidgetDataManage.Getters,
    private val packageName: String,
) : RemoteViewsService.RemoteViewsFactory {

    private var widgetData: WidgetScheduleState = WidgetScheduleState.ErrorScheduleNotSelected
    private var currentDay = 0
    private var isNightTheme = false
    override fun onDataSetChanged() {
        currentDay = manageWidgetData.getCurrentDayInt()
        widgetData = manageWidgetData.getCurrentSchedule()
        isNightTheme = manageWidgetData.isNightModeOn()
    }


    override fun getViewAt(position: Int): RemoteViews {
        return widgetData.inflateRemoteView(
            packageName = packageName,
            position = position,
            currentDay = currentDay,
            isNightTheme = isNightTheme
        )
    }

    override fun getCount(): Int = widgetData.getCountItems(currentDay)
    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun getLoadingView(): RemoteViews? = null
}