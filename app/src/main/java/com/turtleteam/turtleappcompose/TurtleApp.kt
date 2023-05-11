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
            androidLogger(if (BuildConfig.BUILD_TYPE != "release") Level.ERROR else Level.DEBUG)

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

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = NotificationService.CHANNEL_ID
            val descriptionText = NotificationService.CHANNEL_ID
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(NotificationService.CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val intent: Intent by inject(parameters = { parametersOf(this) })
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = "NOTIFICATIONS"

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, NotificationService.CHANNEL_ID)
            .setContentTitle("message.notification?.title")
            .setContentText("message.notification?.body")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.ic_logo)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText("message.notification?.body")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        manager.notify(uniqueNotificationId(), builder.build())
    }
    private fun uniqueNotificationId(): Int {
        val timeLongString = System.currentTimeMillis().toString()
        val idString = timeLongString.substring(timeLongString.length - 5, timeLongString.length)
        return idString.toInt()
    }
}