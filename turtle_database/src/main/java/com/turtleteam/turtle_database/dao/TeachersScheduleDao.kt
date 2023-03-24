package com.turtleteam.turtle_database.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.TeachersDaysList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface TeachersScheduleDao {

    fun getTeacherDaysList(name: String): Flow<TeachersDaysList?>

    suspend fun saveTeacherDaysList(days: String, name: String)

    suspend fun getSavedScheduleList(): List<String>?
}

internal class TeachersScheduleDaoImpl(database: TurtleDatabase) : TeachersScheduleDao {

    private val query = database.turtleDatabaseQueries

    override fun getTeacherDaysList(name: String): Flow<TeachersDaysList?> = query.selectTeacherScheduleByName(name).asFlow().map { it.executeAsOneOrNull() }

    override suspend fun saveTeacherDaysList(days: String, name: String) =
        withContext(Dispatchers.IO) {
            query.insertTeacher(days, name)
        }

    override suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.IO) {
        query.getSavedTeachersList().executeAsList()
    }

}