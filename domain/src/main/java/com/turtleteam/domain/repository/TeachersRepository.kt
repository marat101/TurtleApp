package com.android.turtleapp.data.repository.interfaces

import com.turtleteam.domain.model.schedule.DaysList

interface TeachersRepository {

    suspend fun getSchedule(group: String): Result<DaysList>

    suspend fun getSavedSchedule(group: String): Result<DaysList>

    suspend fun saveSchedule(schedule: DaysList)

    fun getSavedName(): String?

    fun saveName(string: String)

    suspend fun getTeachersList(): List<String>

    fun getPinnedList(): List<String>

    fun savePinnedList(list: List<String>)
}