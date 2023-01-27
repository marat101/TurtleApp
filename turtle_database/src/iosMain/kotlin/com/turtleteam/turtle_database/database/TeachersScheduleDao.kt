package com.turtleteam.turtle_database.database

import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtledatabase.TeachersDaysList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class TeachersScheduleDao actual constructor(database: TurtleDatabase) {

    private val query = database.turtleDatabaseQueries

    actual suspend fun getTeacherDaysList(name: String): TeachersDaysList =
        withContext(Dispatchers.Default) {
            query.selectTeacherScheduleByName(name).executeAsOne()
        }

    actual suspend fun saveTeacherDaysList(days: String, name: String) =
        withContext(Dispatchers.Default) {
            query.insertTeacher(days, name)
        }

    actual suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.Default) {
        query.getSavedTeachersList().executeAsList()
    }

}