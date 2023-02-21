package com.turtleteam.widget_schedule.utils

import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair

object ScheduleFormatter {

    fun getFormattedList(day: Day): List<Pair> {
        val pairs = mutableListOf<Pair>()
        for (pairlist in day.apairs) {
            for (pair in pairlist.apair) {
                pairs.add(pair)
            }
        }
        return pairs
    }
}