package com.turtleteam.turtleappcompose.widget

import com.turtleteam.domain.model.schedule.DaysList

sealed interface WidgetScheduleState{
    fun getSize(day:Int):Int

    object Init : WidgetScheduleState {
        override fun getSize(day: Int): Int = 1
    }
    class Success(val data: DaysList): WidgetScheduleState {
        override fun getSize(day:Int):Int = data.days[day].apairs.count()
    }

    object Error : WidgetScheduleState {
        override fun getSize(day: Int): Int = 1
    }
}