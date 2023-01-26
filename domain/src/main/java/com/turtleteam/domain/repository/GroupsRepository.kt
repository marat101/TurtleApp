package com.android.turtleapp.data.repository.interfaces

import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList


interface GroupsRepository {
    suspend fun getSchedule(group: String): States<DaysList>

    suspend fun getSavedSchedule(group: String): States<DaysList>

    suspend fun saveSchedule(schedule: DaysList)

    fun getSavedName(): String?

    fun saveName(string: String)

    suspend fun getGroupsList(): List<String>

    fun getPinnedList(): List<String>

    fun savePinnedList(list: List<String>)

    fun getLastTargetGroup():String

    fun setLastTargetGroup(group:String)
}