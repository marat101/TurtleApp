package com.turtleteam.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
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
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class ScheduleWidgetProvider : AppWidgetProvider(),KoinComponent {
    private val sch:GetGroupScheduleUseCase by inject(named("GroupUseCase"))
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach { appWidgetId ->
            updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(appWidgetManager,appWidgetId, context)
            )
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {
        val appWidgetId = intent?.extras?.getInt("id") ?: 0
        Log.d("xdd", "onReceive: $appWidgetId ${intent?.action}")
        if (intent?.action == ActionsScheduleWidget.ACTION_REFRESH.actionName && appWidgetId != 0) {
            GlobalScope.launch {
                val group =WidgetDataManage.Getters.Base(context!!).getCurrentGroupName()
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
        if (intent?.action == ActionsScheduleWidget.ACTION_NEXT_DAY.actionName && appWidgetId != 0) {
            if (WidgetDataManage.UpdateCurrentDay.Base(context!!).setNextDay())
                updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(AppWidgetManager.getInstance(context),appWidgetId, context)
            )
        }
        if (intent?.action == ActionsScheduleWidget.ACTION_PREVIOUS_DAY.actionName && appWidgetId != 0) {
            if (WidgetDataManage.UpdateCurrentDay.Base(context!!).setPreviousDay())
                updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget,context,appWidgetId),
                NotifyWidget.Base(AppWidgetManager.getInstance(context),appWidgetId, context)
            )
        }
        super.onReceive(context, intent)
    }
}

fun updateScheduleWidget(inflater:InflateWidget,notifier:NotifyWidget) {
    inflater.inflateBtnPrev()
    inflater.inflateBtnNext()
    inflater.inflateBtnRefresh()
    inflater.inflateBtnCurrentGroup()
    inflater.inflateCurrentDay()
    inflater.inflateDayCounter()
    inflater.inflateLiseView()
    inflater.inflateRootBackground()
    notifier.listViewWasUpdated()
    notifier.widgetWasUpdated(inflater.getView())
}
