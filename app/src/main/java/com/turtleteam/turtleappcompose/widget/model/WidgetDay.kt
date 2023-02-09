package com.turtleteam.turtleappcompose.widget.model

import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair

data class WidgetDay(
    val apairs: List<Pair>,
    val day: String
){
    companion object{
        fun fromDays(day: Day): WidgetDay {
            val list = mutableListOf<Pair>()
            day.apairs.forEach { list += it.apair }
            return WidgetDay(
                apairs = list,
                day = day.day
            )
        }
    }
}