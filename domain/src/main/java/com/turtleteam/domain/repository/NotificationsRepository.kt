package com.turtleteam.domain.repository

import com.turtleteam.domain.model.notifications.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {

    //TODO

    suspend fun saveNotification(notification: Notification)

    fun getNotification(): Flow<List<Notification>>
}