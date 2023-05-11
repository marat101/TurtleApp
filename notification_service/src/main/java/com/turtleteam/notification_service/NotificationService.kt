package com.turtleteam.notification_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.turtleteam.notification_service.workers.SaveNotificationWorker
import com.turtleteam.notification_service.workers.SaveTokenWorker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import ru.turtleteam.theme.R

class NotificationService : FirebaseMessagingService(), KoinComponent {

    companion object {
        const val ACTION_NEW_TOKEN = "com.google.firebase.messaging.NEW_TOKEN"
        const val ACTION_RECEIVE = "com.google.android.c2dm.intent.RECEIVE"

        const val CHANNEL_ID = "Тестовые сообщения"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        setMessage(message)
    }

    override fun handleIntent(intent: Intent?) {
        if (intent?.action == ACTION_RECEIVE) {
            val data = intent.extras as Bundle
            val message = RemoteMessage(data)
            onMessageReceived(message)
        } else {
            super.handleIntent(intent)
        }
    }

    private fun setMessage(message: RemoteMessage) {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = CHANNEL_ID
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        SaveNotificationWorker.enqueueWork(this, message)

        val intent: Intent by inject(parameters = { parametersOf(this) })
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = "NOTIFICATIONS"

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.ic_logo)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message.notification?.body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        manager.notify(uniqueNotificationId(), builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        SaveTokenWorker.enqueueWork(this, token)
    }

    private fun uniqueNotificationId(): Int {
        val timeLongString = System.currentTimeMillis().toString()
        val idString = timeLongString.substring(timeLongString.length - 5, timeLongString.length)
        return idString.toInt()
    }
}