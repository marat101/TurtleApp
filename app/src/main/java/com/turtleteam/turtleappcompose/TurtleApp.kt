package com.turtleteam.turtleappcompose

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.turtleteam.data.di.dataModule
import com.turtleteam.ktor_client.di.networkModule
import com.turtleteam.notification_service.NotificationService
import com.turtleteam.notification_service.workers.SaveNotificationWorker
import com.turtleteam.remote_database.UpdateService
import com.turtleteam.remote_database.di.firestoreModule
import com.turtleteam.turtle_database.di.databaseModule
import com.turtleteam.turtleappcompose.di.domainModule
import com.turtleteam.turtleappcompose.di.repositoryModule
import com.turtleteam.ui.di.uiModule
import com.turtleteam.widget_schedule.di.widgetModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.parameter.parametersOf
import ru.turtleteam.theme.R


class TurtleApp : Application() {

    private val applicationScope = MainScope()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)

            androidContext(this@TurtleApp)
            modules(
                listOf(
                    dataModule,
                    repositoryModule,
                    uiModule,
                    domainModule,
                    databaseModule,
                    networkModule,
                    firestoreModule,
                    widgetModule
                )
            )
        }

        val updateService: UpdateService by inject()

        applicationScope.launch(Dispatchers.IO) {
            updateService.getLatestVersion()
        }
    }
}