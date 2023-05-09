package com.turtleteam.turtle_database.dao

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtledatabase.Notification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

interface NotificationsDao {

    suspend fun saveNotif(title: String, description: String, time: Long)

    fun <T> getNotifications(transform: (Notification) -> T): Flow<List<T>>
}

class NotificationsDaoImpl(database: TurtleDatabase) : NotificationsDao {

    private val query = database.turtleDatabaseQueries

    override suspend fun saveNotif(title: String, description: String, time: Long) =
        withContext(Dispatchers.IO) {
            query.insertNotification(title, description, time)
        }

    override fun <T> getNotifications(transform: (Notification) -> T): Flow<List<T>> =
        query.getNotifications().asFlow().map { it.executeAsList().map(transform) }
}