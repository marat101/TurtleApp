package com.turtleteam.widget_schedule.service

import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.android.turtleapp.data.model.schedule.Pair
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.domain.usecases_impl.groups.GetGroupScheduleUseCase
import com.turtleteam.domain.usecases_impl.teachers.GetTeacherScheduleUseCase
import com.turtleteam.widget_schedule.R
import com.turtleteam.widget_schedule.utils.ScheduleFormatter
import com.turtleteam.widget_schedule.widgetprovider.ScheduleWidgetProvider
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class ScheduleViewService : RemoteViewsService(), KoinComponent {

    companion object {
        const val TIME_ICON = "⏳"
        const val DOCTRINE_ICON = "\uD83D\uDCD6"
        const val TEACHER_ICON = "\uD83C\uDF93"
        const val AUDITORIA_ICON = "\uD83D\uDD11"
        const val CORPUS_ICON = "\uD83C\uDFE2"

        var pairs = mutableListOf<Pair>()
    }

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)


    private val groupsSchedule: GetScheduleUC by inject(named("Groups"))
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return object : RemoteViewsFactory {
            override fun onCreate() {
            }

            override fun onDataSetChanged() {
                scope.launch {
                    pairs = ScheduleFormatter.getFormattedList(groupsSchedule.execute("ИС-23").days.first()).toMutableList()
                }
            }

            override fun onDestroy() {
                job.cancel()
            }

            override fun getCount(): Int = pairs.size

            override fun getViewAt(position: Int): RemoteViews {
                val remoteView =
                    RemoteViews(applicationContext.packageName, R.layout.layout_pair_item).apply {
                        setTextViewText(R.id.widget_pairNumber, "${pairs[position].number} ПАРА")
                        setTextViewText(
                            R.id.widget_time,
                            "${TIME_ICON} ${pairs[position].start} - ${pairs[position].end}"
                        )
                        setTextViewText(R.id.widget_doctrine, "${DOCTRINE_ICON} ${pairs[position].doctrine}")
                        setTextViewText(
                            R.id.widget_name,
                            "${TEACHER_ICON} ${pairs[position].teacher}"
                        )
                        setTextViewText(R.id.widget_auditoria, "${AUDITORIA_ICON} ${pairs[position].auditoria}")
                        setTextViewText(R.id.widget_corpus, "${CORPUS_ICON} ${pairs[position].corpus}")
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
    }
}