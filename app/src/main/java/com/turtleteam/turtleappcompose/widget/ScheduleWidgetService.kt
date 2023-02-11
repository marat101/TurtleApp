package com.turtleteam.turtleappcompose.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.turtleteam.turtleappcompose.widget.utils.WidgetDataManage

class ScheduleWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return RemoteViewsFactory(WidgetDataManage.Getters.Base(this),this.packageName)
    }
}

