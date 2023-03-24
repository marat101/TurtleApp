package com.turtleteam.domain.repository

import com.turtleteam.domain.model.schedule.DaysList
import kotlinx.coroutines.flow.Flow


interface ScheduleRepository {
    suspend fun getSchedule(name: String): DaysList

    fun getSavedSchedule(name: String): Flow<DaysList?>

    suspend fun saveSchedule(schedule: DaysList)

    fun getSavedName(): String?

    fun saveName(string: String)

    suspend fun getNamesList(): List<String>

    suspend fun getSavedNamesList(): List<String>?

    fun getPinnedList(): List<String>

    fun savePinnedList(list: List<String>)

    fun getLastTargetName(): String

    fun setLastTargetName(name: String)
}