package com.turtleteam.widget.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.widget.R
import com.turtleteam.widget.widget.model.ActionsScheduleWidget
import com.turtleteam.widget.widget.utils.InflateWidget
import com.turtleteam.widget.widget.utils.NotifyWidget
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class ScheduleWidgetProvider : AppWidgetProvider(), KoinComponent {
    private val sch: GetGroupScheduleUseCase by inject(named("GroupUseCase"))
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        appWidgetIds.forEach { appWidgetId ->
            updateScheduleWidget(
                InflateWidget.Base(R.layout.schedule_widget, context, appWidgetId),
                NotifyWidget.Base(appWidgetManager, appWidgetId, context)
            )
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        ActionsScheduleWidget.values().firstOrNull {
            it.actionName == intent?.action
        }?.let { action ->
            WidgetOnReceiveIntentProcessing.Base(
                context = context!!,
                appWidgetId = intent?.extras?.getInt("id") ?: throw IllegalArgumentException(),
                sch = sch
            ).apply {
                handleAction(action = action)
            }
        }
        super.onReceive(context, intent)
    }
}

fun updateScheduleWidget(inflater: InflateWidget, notifier: NotifyWidget) {
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
