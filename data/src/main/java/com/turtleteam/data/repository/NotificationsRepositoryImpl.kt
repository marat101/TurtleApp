package com.turtleteam.data.repository

import com.turtleteam.domain.model.notifications.Notification
import com.turtleteam.domain.repository.NotificationsRepository
import com.turtleteam.turtle_database.dao.NotificationsDao
import kotlinx.coroutines.flow.Flow

class NotificationsRepositoryImpl(private val notificationsDao: NotificationsDao): NotificationsRepository {

    override suspend fun saveNotification(notification: Notification) {
        notificationsDao.saveNotif(notification.title, notification.description, notification.sentTime)
    }

    override fun getNotification(): Flow<List<Notification>> = notificationsDao.getNotifications { Notification(it.title, it.description, it.sentTime) }
}