package com.turtleteam.widget.widget.model

import android.content.Context
import android.widget.RemoteViews
import com.turtleteam.widget.R
import com.turtleteam.widget.widget.utils.ManageWidgetColor
import com.turtleteam.widget.widget.utils.UpdateWidgetHelper

sealed interface WidgetScheduleState {
    fun getCountItems(day: Int): Int
    fun getCountDays(): Int
    fun getCurrentDay(day: Int): String
    fun inflateRemoteView(
        packageName: String,
        position: Int,
        currentDay: Int,
        isNightTheme: Boolean,
    ): RemoteViews

    object ErrorScheduleNotSelected : WidgetScheduleState {
        override fun getCountDays(): Int = 0
        override fun getCurrentDay(day: Int): String = ""
        override fun getCountItems(day: Int): Int = 1
        override fun inflateRemoteView(
            packageName: String,
            position: Int,
            currentDay: Int,
            isNightTheme: Boolean,
        ): RemoteViews {
            return RemoteViews(packageName, R.layout.widget_init_state)
        }
    }

    class Success(
        private val data: WidgetSuccessStateData,
        private val context: Context,
    ) : WidgetScheduleState {
        override fun getCountDays(): Int = data.getCountDays()
        override fun getCurrentDay(day: Int): String = data.getDayName(day)
        override fun getCountItems(day: Int): Int = data.getCountApairs(day)
        override fun inflateRemoteView(
            packageName: String,
            position: Int,
            currentDay: Int,
            isNightTheme: Boolean,
        ): RemoteViews {
            val view = RemoteViews(packageName, R.layout.item_one_pair_widget)
            val colorManager = ManageWidgetColor.Base()
            UpdateWidgetHelper.Base().apply {
                setText(
                    view = view,
                    viewId = R.id.widgetApairNumber,
                    text = data.getNumber(currentDay, position, context),
                    color = context.getColor(colorManager.getTitleTextColor(isNightTheme))
                )
                setText(
                    view = view,
                    viewId = R.id.widgetTimeText,
                    text = data.getTime(currentDay, position),
                    color = context.getColor(colorManager.getSecondTextColor(isNightTheme))
                )
                setText(
                    view = view,
                    viewId = R.id.widgetDoctrineText,
                    text = data.getDoctrine(currentDay, position),
                    color = context.getColor(colorManager.getSecondTextColor(isNightTheme))
                )
                setText(
                    view = view,
                    viewId = R.id.widgetTeacherText,
                    text = data.getTeacher(currentDay,position),
                    color = context.getColor(colorManager.getSecondTextColor(isNightTheme))
                )
                setText(
                    view = view,
                    viewId = R.id.widgetAuditoriaText,
                    text = data.getAuditoria(currentDay, position),
                    color = context.getColor(colorManager.getSecondTextColor(isNightTheme))
                )
                setText(
                    view = view,
                    viewId = R.id.widgetCorpusText,
                    text = data.getCorpus(currentDay, position),
                    color = context.getColor(colorManager.getSecondTextColor(isNightTheme))
                )
            }
            return view
        }
    }
}