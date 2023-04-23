package com.turtleteam.widget_schedule.service

import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.utils.ScheduleFormatter
import com.turtleteam.widget_schedule.widgetprovider.ScheduleWidgetProvider
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScheduleViewService : RemoteViewsService(), KoinComponent {

    companion object {
        const val PAIRS_EXTRA = "pairs"
        const val EXTRA_DAY = "targetday"

        const val TIME_ICON = "⏳"
        const val DOCTRINE_ICON = "\uD83D\uDCD6"
        const val TEACHER_ICON = "\uD83C\uDF93"
        const val AUDITORIA_ICON = "\uD83D\uDD11"
        const val CORPUS_ICON = "\uD83C\uDFE2"
    }

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {

        return ScheduleViewsFactory(applicationContext)
    }
}