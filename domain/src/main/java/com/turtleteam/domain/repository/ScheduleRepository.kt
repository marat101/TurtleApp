package com.turtleteam.domain.repository

import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.schedule.DaysList


interface ScheduleRepository {
    suspend fun getSchedule(name: String): States<DaysList>

    suspend fun getSavedSchedule(name: String): States<DaysList>

    suspend fun saveSchedule(schedule: DaysList)

    fun getSavedName(): String?

    fun saveName(string: String)

    suspend fun getNamesList(): List<String>

    fun getPinnedList(): List<String>

    fun savePinnedList(list: List<String>)

    fun getLastTargetName(): String

    fun setLastTargetName(name: String)
}