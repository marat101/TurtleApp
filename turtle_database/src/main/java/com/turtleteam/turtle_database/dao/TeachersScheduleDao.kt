package com.turtleteam.turtle_database.dao

import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.TeachersDaysList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TeachersScheduleDao {

    suspend fun getTeacherDaysList(name: String): TeachersDaysList

    suspend fun saveTeacherDaysList(days: String, name: String)

    suspend fun getSavedScheduleList(): List<String>
}

internal class TeachersScheduleDaoImpl(database: TurtleDatabase): TeachersScheduleDao {

    private val query = database.turtleDatabaseQueries

    override suspend fun getTeacherDaysList(name: String): TeachersDaysList =
        withContext(Dispatchers.IO) {
            query.selectTeacherScheduleByName(name).executeAsOne()
        }

    override suspend fun saveTeacherDaysList(days: String, name: String) =
        withContext(Dispatchers.IO) {
            query.insertTeacher(days, name)
        }

    override suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.IO) {
        query.getSavedTeachersList().executeAsList()
    }

}