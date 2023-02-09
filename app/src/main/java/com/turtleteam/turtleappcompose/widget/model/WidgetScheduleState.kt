package com.turtleteam.turtleappcompose.widget.model

import android.content.Context
import android.widget.RemoteViews
import com.turtleteam.turtleappcompose.R

sealed interface WidgetScheduleState {
    fun getCountItems(day: Int): Int
    fun inflateRemoteView(packageName: String, position: Int, currentDay: Int): RemoteViews

    object ErrorScheduleNotSelected : WidgetScheduleState {
        override fun getCountItems(day: Int): Int = 1
        override fun inflateRemoteView(
            packageName: String,
            position: Int,
            currentDay: Int,
        ): RemoteViews {
            return RemoteViews(packageName, R.layout.widget_init_state)
        }
    }

    class Success(
        private val data: WidgetSuccessStateData,
        private val context: Context,
    ) : WidgetScheduleState {
        override fun getCountItems(day: Int): Int = data.getCountApairs(day)
        fun getCountDays() = data.getCountDays()
        fun getCurrentDay(day: Int): String = data.getDayName(day)
        override fun inflateRemoteView(
            packageName: String,
            position: Int,
            currentDay: Int,
        ): RemoteViews {
            val view = RemoteViews(packageName, R.layout.item_one_pair_widget)
            view.setTextViewText(R.id.widgetApairNumber, data.getNumber(currentDay, position, context))
            view.setTextViewText(R.id.widgetTimeText, data.getTime(currentDay, position))
            view.setTextViewText(R.id.widgetDoctrineText, data.getDoctrine(currentDay, position))
            view.setTextViewText(R.id.widgetTeacherText, data.getTeacher(currentDay, position))
            view.setTextViewText(R.id.widgetAuditoriaText, data.getAuditoria(currentDay, position))
            view.setTextViewText(R.id.widgetCorpusText, data.getCorpus(currentDay, position))

            return view
        }
    }

    object Error : WidgetScheduleState {
        override fun getCountItems(day: Int): Int = 1
        override fun inflateRemoteView(
            packageName: String,
            position: Int,
            currentDay: Int,
        ): RemoteViews {
            return RemoteViews(packageName, R.layout.widget_error_state)
        }
    }
}