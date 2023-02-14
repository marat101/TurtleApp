package com.turtleteam.widget.widget.model

import android.content.Context
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.turtleteam.widget.R
import com.turtleteam.widget.widget.utils.ManageWidgetColor

sealed interface WidgetScheduleState {
    fun getCountItems(day: Int): Int
    fun inflateRemoteView(
        packageName: String,
        position: Int,
        currentDay: Int,
        isNightTheme: Boolean,
    ): RemoteViews

    object ErrorScheduleNotSelected : WidgetScheduleState {
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
        val color = ManageWidgetColor.Base()
        override fun getCountItems(day: Int): Int = data.getCountApairs(day)
        fun getCountDays() = data.getCountDays()
        fun getCurrentDay(day: Int): String = data.getDayName(day)
        override fun inflateRemoteView(
            packageName: String,
            position: Int,
            currentDay: Int,
            isNightTheme: Boolean,
        ): RemoteViews {
            val view = RemoteViews(packageName, R.layout.item_one_pair_widget)
            view.setTextViewText(
                R.id.widgetApairNumber,
                data.getNumber(currentDay, position, context)
            )
            view.setTextColor(
                R.id.widgetApairNumber,
                context.getColor(
                    color.getTitleTextColor(isNightTheme)
                )
            )
            updateTextView(
                view = view,
                isNightTheme = isNightTheme,
                textViewId = R.id.widgetTimeText,
                text = data.getTime(currentDay, position)
            )
            updateTextView(
                view = view,
                isNightTheme = isNightTheme,
                textViewId = R.id.widgetDoctrineText,
                text = data.getDoctrine(currentDay, position)
            )
            updateTextView(
                view = view,
                isNightTheme = isNightTheme,
                textViewId = R.id.widgetTeacherText,
                text = data.getTeacher(currentDay, position)
            )
            updateTextView(
                view = view,
                isNightTheme = isNightTheme,
                textViewId = R.id.widgetAuditoriaText,
                text = data.getAuditoria(currentDay, position)
            )
            updateTextView(
                view = view,
                isNightTheme = isNightTheme,
                textViewId = R.id.widgetCorpusText,
                text = data.getCorpus(currentDay, position)
            )

            return view
        }

        private fun updateTextView(
            view: RemoteViews,
            isNightTheme: Boolean,
            @IdRes textViewId: Int,
            text: String,
        ) {
            view.setTextViewText(textViewId, text)
            view.setTextColor(
                textViewId,
                context.getColor(color.getSecondTextColor(isNightTheme))
            )
        }
    }
}