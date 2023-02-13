package com.turtleteam.widget.widget.utils

import android.content.Context
import android.widget.RemoteViews
import com.turtleteam.widget.R
import com.turtleteam.widget.WidgetActivity
import com.turtleteam.widget.widget.ScheduleWidgetProvider
import com.turtleteam.widget.widget.ScheduleWidgetService
import com.turtleteam.widget.widget.model.ActionsScheduleWidget

interface InflateWidget {
    fun inflateBtnPrev()
    fun inflateBtnNext()
    fun inflateBtnRefresh()
    fun inflateBtnCurrentGroup()
    fun inflateCurrentDay()
    fun inflateDayCounter()
    fun inflateLiseView()
    fun inflateRootBackground()

    fun getView(): RemoteViews
    class Base private constructor(
        private val widgetLayoutId: Int,
        private val context: Context,
        private val dataManage: WidgetDataManage.Getters,
        private val updateWidget: UpdateWidget,
        private val managePendingIntent: ManagePendingIntent,
        private val drawable: ManageWidgetDrawable,
        private val color: ManageWidgetColor,

        ) : InflateWidget {
        constructor(
            widgetLayoutId: Int,
            context: Context,
            widgetId: Int,
        ) : this(
            widgetLayoutId = widgetLayoutId,
            context = context,
            dataManage = WidgetDataManage.Getters.Base(context),
            updateWidget = UpdateWidget.Base(context, widgetId),
            managePendingIntent = ManagePendingIntent.Base(context, widgetId),
            drawable = ManageWidgetDrawable.Base(),
            color = ManageWidgetColor.Base()
        )

        private val view = RemoteViews(context.packageName, widgetLayoutId)
        private val isNightMode = dataManage.isNightModeOn()

        override fun getView() = view
        override fun inflateBtnPrev() {
            updateWidget.setOnClickListener(
                view,
                R.id.btnPrev,
                managePendingIntent.createPendingBroadcastIntent(
                    ActionsScheduleWidget.ACTION_PREVIOUS_DAY,
                    ScheduleWidgetProvider::class.java
                )
            )
        }

        override fun inflateBtnNext() {
            updateWidget.setOnClickListener(
                view,
                R.id.btnNext,
                managePendingIntent.createPendingBroadcastIntent(
                    ActionsScheduleWidget.ACTION_NEXT_DAY,
                    ScheduleWidgetProvider::class.java
                )
            )
        }

        override fun inflateBtnRefresh() {
            updateWidget.setOnClickListener(
                view,
                R.id.btnRefresh,
                managePendingIntent.createPendingBroadcastIntent(
                    ActionsScheduleWidget.ACTION_REFRESH,
                    ScheduleWidgetProvider::class.java
                )
            )
        }

        override fun inflateBtnCurrentGroup() {
            updateWidget.setOnClickListener(
                view,
                R.id.widgetCurrentGroup,
                managePendingIntent.createPendingOpenActivity(WidgetActivity::class.java)
            )
            updateWidget.setText(
                view,
                R.id.widgetCurrentGroup,
                dataManage.getCurrentGroupName(),
                context.getColor(
                    color.getTitleTextColor(isNightMode)
                )
            )
            updateWidget.setBackground(
                view,
                R.id.widgetCurrentGroup,
                drawable.getBackground(isNightMode)
            )
        }

        override fun inflateCurrentDay() {
            updateWidget.setText(
                view,
                R.id.widgetDay,
                dataManage.getCurrentDayString(),
                context.getColor(
                    color.getTitleTextColor(isNightMode)
                )
            )
        }

        override fun inflateDayCounter() {
            updateWidget.setText(
                view,
                R.id.widgetDayCount,
                dataManage.getDaysCount(),
                context.getColor(color.getDayCountTextColor(isNightMode))
            )
        }

        override fun inflateLiseView() {
            updateWidget.setListViewAdapter(view, R.id.listView, ScheduleWidgetService::class.java)
        }

        override fun inflateRootBackground() {
            updateWidget.setBackground(
                view,
                R.id.widgetRoot,
                drawable.getRootBackground(isNightMode)
            )
        }
    }
}