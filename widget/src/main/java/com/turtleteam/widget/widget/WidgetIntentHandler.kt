package com.turtleteam.widget.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.util.Log
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.widget.R
import com.turtleteam.widget.widget.model.ActionsScheduleWidget
import com.turtleteam.widget.widget.utils.InflateWidget
import com.turtleteam.widget.widget.utils.NotifyWidget
import com.turtleteam.widget.widget.utils.WidgetDataManage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class WidgetOnReceiveIntentProcessing {
    fun handleAction(action: ActionsScheduleWidget){
        when (action) {
            ActionsScheduleWidget.ACTION_NEXT_DAY -> actionNextDay()
            ActionsScheduleWidget.ACTION_REFRESH -> actionRefresh()
            ActionsScheduleWidget.ACTION_PREVIOUS_DAY -> actionPreviousDay()
        }
    }

    protected abstract fun actionNextDay()
    protected abstract fun actionRefresh()
    protected abstract fun actionPreviousDay()


    class Base(
        private val context: Context,
        private val appWidgetId: Int,
        private val sch: GetGroupScheduleUseCase,
    ) : WidgetOnReceiveIntentProcessing(){

        override fun actionNextDay() {
            Log.d("xdd","actionNextDay")
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setNextDay())
                updateScheduleWidget(
                    InflateWidget.Base(R.layout.schedule_widget, context, appWidgetId),
                    NotifyWidget.Base(AppWidgetManager.getInstance(context), appWidgetId, context)
                )
        }

        @OptIn(DelicateCoroutinesApi::class)
        override fun actionRefresh() {
            Log.d("xdd","actionRefresh")
            GlobalScope.launch {
                val group = WidgetDataManage.Getters.Base(context).getCurrentGroupName()
                val schedule = sch.execute(group)
                if (schedule is States.Success) {
                    val setter = WidgetDataManage.SetData.Base(context)
                    setter.setSchedule(DaysList.toJson(schedule.value))
                    setter.setFirstDay()
                    updateScheduleWidget(
                        InflateWidget.Base(R.layout.schedule_widget, context, appWidgetId),
                        NotifyWidget.Base(
                            AppWidgetManager.getInstance(context),
                            appWidgetId,
                            context
                        )
                    )
                }
            }
        }
        override fun actionPreviousDay() {
            Log.d("xdd","actionPreviousDay")
            if (WidgetDataManage.UpdateCurrentDay.Base(context).setPreviousDay())
                updateScheduleWidget(
                    InflateWidget.Base(R.layout.schedule_widget, context, appWidgetId),
                    NotifyWidget.Base(AppWidgetManager.getInstance(context), appWidgetId, context)
                )
        }

    }
}