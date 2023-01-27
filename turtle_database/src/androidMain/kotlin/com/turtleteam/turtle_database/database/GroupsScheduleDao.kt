package com.turtleteam.turtle_database.database

import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtledatabase.GroupsDaysList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class GroupsScheduleDao actual constructor(database: TurtleDatabase) {

    private val query = database.turtleDatabaseQueries

    actual suspend fun getGroupDaysList(name: String): GroupsDaysList =
        withContext(Dispatchers.IO) {
            query.selectGroupScheduleByName(name).executeAsOne()
        }

    actual suspend fun saveGroupDaysList(days: String, name: String) = withContext(Dispatchers.IO) {
        query.insertGroup(days, name)
    }

    actual suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.IO) {
        query.getSavedGroupsList().executeAsList()
    }
}