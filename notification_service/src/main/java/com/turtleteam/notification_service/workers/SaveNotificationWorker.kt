package com.turtleteam.notification_service.workers

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
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

            val constraints = Constraints.Builder().setRequiresBatteryNotLow(true).build()

            val workRequest = OneTimeWorkRequestBuilder<SaveNotificationWorker>().setInputData(
                Data.Builder()
                    .putString(TITLE,message.notification?.title)
                    .putString(DESCRIPTION,message.notification?.body)
                    .putLong(TIME, message.sentTime)
                    .build()
            ).setConstraints(constraints).build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

    override fun setProgressAsync(data: Data): ListenableFuture<Void> {
        return super.setProgressAsync(workerParams.inputData)
    }

    override suspend fun doWork(): Result {
        val title = workerParams.inputData.getString(TITLE) ?: ""
        val description = workerParams.inputData.getString(DESCRIPTION) ?: ""
        val time = workerParams.inputData.getLong(TIME, System.currentTimeMillis())

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