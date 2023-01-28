package com.turtleteam.data.converter

import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair
import com.android.turtleapp.data.model.schedule.PairsList
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {


    fun dayslistToGson(list: List<Day>): String = Json.encodeToString(list)


    fun gsonToDaysList(string: String): List<Day> {
        return Json.decodeFromString(string)
    }


    fun pairsListToGson(list: List<PairsList>): String = Json.encodeToString(list)


    fun gsonToPairsList(string: String): List<PairsList> {
        return Json.decodeFromString(string)
    }


    fun pairlistToGson(list: List<Pair>): String = Json.encodeToString(list)


    fun gsonToPairList(string: String): List<Pair> {
        return Json.decodeFromString(string)
    }


    fun stringToGson(list: List<String>): String = Json.encodeToString(list)


    fun gsonToString(string: String): List<String> {
        return Json.decodeFromString(string)
    }
}