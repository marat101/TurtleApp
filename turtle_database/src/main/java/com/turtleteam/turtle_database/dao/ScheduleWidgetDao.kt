package com.turtleteam.turtle_database.dao

import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.ScheduleWidgetState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ScheduleWidgetDao {

    suspend fun getWidgetById(): ScheduleWidgetState?

    suspend fun insertWidget(id: Int, schedule: String, page: Int, isGroup: Boolean)

    suspend fun deleteWidget()
}

internal class ScheduleWidgetDaoImpl(database: TurtleDatabase) : ScheduleWidgetDao {

    private val query = database.turtleDatabaseQueries

    override suspend fun getWidgetById(): ScheduleWidgetState? = withContext(Dispatchers.IO) {
        query.getScheduleWidgetState().executeAsOneOrNull()
    }

    override suspend fun insertWidget(id: Int, schedule: String, page: Int, isGroup: Boolean) =
        withContext(Dispatchers.IO) {
            val type = if (isGroup) 1 else 0
            query.insertWidgetState(id.toLong(), schedule, page.toLong(), type.toLong())
        }

    override suspend fun deleteWidget() = withContext(Dispatchers.IO) {
        query.deleteScheduleWidgetState()
    }
}