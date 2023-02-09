package com.turtleteam.turtleappcompose.widget

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.turtleteam.turtleappcompose.R

class StackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return StackRemoteViewsFactory(applicationContext)
    }
}

class StackRemoteViewsFactory(
    private val context: Context
) : RemoteViewsService.RemoteViewsFactory {

    //TODO convert apair[0] to list #10
    private var widgetData:WidgetScheduleState = WidgetScheduleState.Init
    private var currentDay = 0

    override fun onDataSetChanged() {
        WidgetDataManage.Getters.Base(context).also {
            currentDay = it.getCurrentDayInt()
            widgetData = it.getCurrentSchedule()
        }

        Log.d("xdd", "list view data was updated " +
                "currentDay:${currentDay.hashCode()} " +
                "widgetData${widgetData.hashCode()}"
        )
    }





    override fun getViewAt(position: Int): RemoteViews {
        return when(widgetData){
            is WidgetScheduleState.Success->{
                //TODO convert apair[0] to list #10
                val item = (widgetData as WidgetScheduleState.Success).data.days[currentDay]
                RemoteViews(context.packageName, R.layout.item_one_pair_widget).apply {
                    setTextViewText(R.id.widgetTimeText, item.apairs[position].time)
                    setTextViewText(R.id.widgetDoctrineText, item.apairs[position].apair[0].doctrine)
                    setTextViewText(R.id.widgetTeacherText, item.apairs[position].apair[0].teacher)
                    setTextViewText(R.id.widgetAuditoriaText, item.apairs[position].apair[0].auditoria)
                    setTextViewText(R.id.widgetCorpusText, item.apairs[position].apair[0].corpus)
                }
            }
            is WidgetScheduleState.Init->{
                //TODO init screen
                RemoteViews(context.packageName, R.layout.item_one_pair_widget)
            }
            is WidgetScheduleState.Error->{
                //TODO init screen
                RemoteViews(context.packageName, R.layout.item_one_pair_widget)
            }
        }


    }
    //TODO change size after #10
    override fun getCount(): Int = widgetData.getSize(currentDay)

    override fun getViewTypeCount(): Int = 1
    override fun getItemId(position: Int): Long = position.toLong()
    override fun hasStableIds(): Boolean = true

    override fun onCreate() {}
    override fun onDestroy() {}
    override fun getLoadingView(): RemoteViews? = null
}