package com.turtleteam.notification_service.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.google.firebase.messaging.RemoteMessage
import com.turtleteam.domain.model.notifications.Notification
import com.turtleteam.domain.repository.NotificationsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveNotificationWorker(
    context: Context,
    private val workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams), KoinComponent {

    companion object {
        const val TITLE = "TITLE"
        const val DESCRIPTION = "DESCRIPTION"
        const val TIME = "TIME"

        fun enqueueWork(context: Context, message: RemoteMessage){
            val workRequest = OneTimeWorkRequestBuilder<SaveNotificationWorker>().setInputData(
                Data.Builder()
                    .putString(TITLE,message.notification?.title)
                    .putString(DESCRIPTION,message.notification?.body)
                    .putLong(TIME, message.sentTime)
                    .build()
            ).build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

    override suspend fun doWork(): Result {
        val title = workerParams.inputData.getString(TITLE) ?: ""
        val description = workerParams.inputData.getString(DESCRIPTION) ?: ""
        val time = workerParams.inputData.getLong(TIME, 0)

        val repository: NotificationsRepository by inject()

        repository.saveNotification(
            Notification(
                title,
                description,
                time
            )
        )

        return Result.success()
    }
}