package com.turtleteam.widget.widget

import android.content.Intent
import android.widget.RemoteViewsService

class ScheduleWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return RemoteViewsFactory(this)
    }
}

