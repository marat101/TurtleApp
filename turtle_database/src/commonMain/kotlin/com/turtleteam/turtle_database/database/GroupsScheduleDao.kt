package com.turtleteam.turtle_database.database

import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtledatabase.GroupsDaysList

expect class GroupsScheduleDao(database: TurtleDatabase) {

    suspend fun getGroupDaysList(name: String): GroupsDaysList

    suspend fun saveGroupDaysList(days: String, name: String)

    suspend fun getSavedScheduleList(): List<String>
}