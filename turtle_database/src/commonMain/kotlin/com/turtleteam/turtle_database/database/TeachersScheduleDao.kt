package com.turtleteam.turtle_database.database

import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtledatabase.TeachersDaysList

expect class TeachersScheduleDao(database: TurtleDatabase) {

    suspend fun getTeacherDaysList(name: String): TeachersDaysList

    suspend fun saveTeacherDaysList(days: String, name: String)

    suspend fun getSavedScheduleList(): List<String>
}