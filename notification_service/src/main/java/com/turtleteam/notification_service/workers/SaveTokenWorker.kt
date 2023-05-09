package com.turtleteam.notification_service.workers

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.turtleteam.domain.repository.SubscriptionsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveTokenWorker(context: Context, private val workerParams: WorkerParameters) :
    Worker(context, workerParams), KoinComponent {

    companion object {
        const val FCM_TOKEN = "fcmtoken"

        fun enqueueWork(context: Context, token: String){
            val workRequest = OneTimeWorkRequestBuilder<SaveTokenWorker>().setInputData(
                Data.Builder()
                    .putString(FCM_TOKEN, token)
                    .build()
            ).build()
            WorkManager.getInstance(context).enqueue(workRequest)
        }
    }

    override fun doWork(): Result {
        val prefs: SubscriptionsRepository by inject()
        val token = workerParams.inputData.getString(FCM_TOKEN)
        prefs.saveFCMToken(token ?: "")
        return Result.success()
    }
}