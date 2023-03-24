package com.turtleteam.turtle_database.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.GroupsDaysList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface GroupsScheduleDao {

    fun getGroupDaysList(name: String): Flow<GroupsDaysList?>

    suspend fun saveGroupDaysList(days: String, name: String)

    suspend fun getSavedScheduleList(): List<String>?
}

internal class GroupsScheduleDaoImpl(database: TurtleDatabase) : GroupsScheduleDao {

    private val query = database.turtleDatabaseQueries

    override fun getGroupDaysList(name: String): Flow<GroupsDaysList?> = query.selectGroupScheduleByName(name).asFlow().map { it.executeAsOneOrNull() }

    override suspend fun saveGroupDaysList(days: String, name: String) =
        withContext(Dispatchers.IO) {
            query.insertGroup(days, name)
        }

    override suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.IO) {
        query.getSavedGroupsList().executeAsList()
    }
}