package com.android.turtleapp.data.local.converter

import androidx.room.TypeConverter
import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair
import com.android.turtleapp.data.model.schedule.PairsList
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {

    @TypeConverter
    fun dayslistToGson(list: List<Day>): String = Json.encodeToString(list)

    @TypeConverter
    fun gsonToDaysList(string: String): List<Day> {
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun pairsListToGson (list: List<PairsList>): String = Json.encodeToString(list)

    @TypeConverter
    fun gsonToPairsList(string: String): List<PairsList> {
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun pairlistToGson(list: List<Pair>): String = Json.encodeToString(list)

    @TypeConverter
    fun gsonToPairList(string: String): List<Pair> {
        return Json.decodeFromString(string)
    }

    @TypeConverter
    fun stringToGson(list: List<String>): String = Json.encodeToString(list)

    @TypeConverter
    fun gsonToString(string: String): List<String> {
        return Json.decodeFromString(string)
    }
}