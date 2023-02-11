package com.turtleteam.turtle_database.dao

import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.ScheduleWidgetState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ScheduleWidgetDao {

    suspend fun getWidgetById(id: Int): ScheduleWidgetState

    suspend fun <T> insertWidget(id: Int, schedule: T, page: Int, isGroup: Boolean)

    suspend fun deleteWidget(id: Int)
}

internal class ScheduleWidgetDaoImpl(database: TurtleDatabase) : ScheduleWidgetDao {

    private val query = database.turtleDatabaseQueries

    override suspend fun getWidgetById(id: Int): ScheduleWidgetState = withContext(Dispatchers.IO) {
        query.getScheduleWidgetState(id.toLong()).executeAsOne()
    }

    override suspend fun <T> insertWidget(id: Int, schedule: T, page: Int, isGroup: Boolean) =
        withContext(Dispatchers.IO) {
            val type = if (isGroup) 1 else 0
            query.insertWidgetState(id.toLong(), schedule.toString(), page.toLong(), type.toLong())
        }

    override suspend fun deleteWidget(id: Int) = withContext(Dispatchers.IO) {
        query.deleteScheduleWidgetState(id.toLong())
    }
}