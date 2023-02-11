package com.turtleteam.widget.widget

import android.content.Intent
import android.widget.RemoteViewsService
import com.turtleteam.widget.widget.utils.WidgetDataManage

class ScheduleWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return RemoteViewsFactory(WidgetDataManage.Getters.Base(this),this.packageName)
    }
}

