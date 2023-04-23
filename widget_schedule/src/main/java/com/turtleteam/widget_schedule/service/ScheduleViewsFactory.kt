package com.turtleteam.widget_schedule.service

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair
import com.turtleteam.domain.usecases_impl.widget.GetScheduleWidget
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.utils.ScheduleFormatter
import com.turtleteam.widget_schedule.widgetprovider.ScheduleWidgetProvider
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScheduleViewsFactory(
    private val context: Context
) : RemoteViewsFactory, KoinComponent {

    private val getState: GetScheduleWidget by inject()

    var pairs = emptyList<Pair>()

    override fun onCreate() {
        val state = getState.execute()
        runCatching {
            pairs = ScheduleFormatter.getFormattedList(
                state.schedule.days[state.page]
            )
        }
    }

    override fun onDataSetChanged() {
        val state = getState.execute()
        runCatching {
            pairs = ScheduleFormatter.getFormattedList(
                state.schedule.days[state.page]
            )
        }
    }

    override fun onDestroy() {
    }

    override fun getCount(): Int = pairs.size

    override fun getViewAt(position: Int): RemoteViews {
        val remoteView =
            RemoteViews(context.packageName, R.layout.layout_pair_item).apply {
                setTextViewText(R.id.widget_pairNumber, "${pairs[position].number} ПАРА")
                setTextViewText(
                    R.id.widget_time,
                    "${ScheduleViewService.TIME_ICON} ${pairs[position].start} - ${pairs[position].end}"
                )
                setTextViewText(
                    R.id.widget_doctrine,
                    "${ScheduleViewService.DOCTRINE_ICON} ${pairs[position].doctrine}"
                )
                setTextViewText(
                    R.id.widget_name,
                    "${ScheduleViewService.TEACHER_ICON} ${pairs[position].teacher}"
                )
                setTextViewText(
                    R.id.widget_auditoria,
                    "${ScheduleViewService.AUDITORIA_ICON} ${pairs[position].auditoria}"
                )
                setTextViewText(
                    R.id.widget_corpus,
                    "${ScheduleViewService.CORPUS_ICON} ${pairs[position].corpus}"
                )
            }
        val extras = Bundle()
        extras.putInt(ScheduleWidgetProvider.EXTRA_ITEM, position)
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        remoteView.setOnClickFillInIntent(R.id.widget_date, fillInIntent)

        return remoteView
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}