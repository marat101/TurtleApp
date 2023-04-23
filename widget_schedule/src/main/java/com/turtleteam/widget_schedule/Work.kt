package com.turtleteam.widget_schedule

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.turtleteam.domain.usecases.SaveScheduleUC
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

class SaveScheduleWork(context: Context, private val workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), KoinComponent {

    companion object {
        const val SCHEDULE_SAVE = "schedule"
        const val SCHEDULE_TYPE = "isgroup"
    }

    override suspend fun doWork(): Result {
        val json: Json by inject()
        return try {
            val schedule = workerParams.inputData.getString(SCHEDULE_SAVE)
            val isGroups = workerParams.inputData.getBoolean(SCHEDULE_TYPE, true)
            val save: SaveScheduleUC by inject(named(if (isGroups) "Groups" else "Teachers"))
            save.execute(json.decodeFromString(schedule!!))
            Log.e("TAGTAG", schedule.toString())
            Result.success()
        } catch (e: Throwable) {
            Log.e("TAGTAG", e.toString())
            Result.failure()
        }
    }
}