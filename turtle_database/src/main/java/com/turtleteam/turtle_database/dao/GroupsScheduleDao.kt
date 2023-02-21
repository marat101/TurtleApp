package com.turtleteam.turtle_database.dao

import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.GroupsDaysList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GroupsScheduleDao {

    suspend fun getGroupDaysList(name: String): GroupsDaysList

    suspend fun saveGroupDaysList(days: String, name: String)

    suspend fun getSavedScheduleList(): List<String>
}

internal class GroupsScheduleDaoImpl(database: TurtleDatabase) : GroupsScheduleDao {

    private val query = database.turtleDatabaseQueries

    override suspend fun getGroupDaysList(name: String): GroupsDaysList =
        withContext(Dispatchers.IO) {
            query.selectGroupScheduleByName(name).executeAsOne()
        }

    override suspend fun saveGroupDaysList(days: String, name: String) =
        withContext(Dispatchers.IO) {
            query.insertGroup(days, name)
        }

    override suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.IO) {
        query.getSavedGroupsList().executeAsList()
    }
}