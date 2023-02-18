package com.turtleteam.domain.model.schedule

import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.domain.model.other.Schedule
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class DaysList(
    override val days: List<Day>,
    override val name: String
) : Schedule{
    companion object{
        fun toJson(data:DaysList):String{
            return Json.encodeToString(data)
        }
        fun toDaysList(string: String):DaysList{
            return Json.decodeFromString(string)
        }
    }
}