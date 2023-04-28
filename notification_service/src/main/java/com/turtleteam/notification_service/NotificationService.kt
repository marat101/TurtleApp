package com.turtleteam.notification_service

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder =  NotificationCompat.Builder(applicationContext)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        builder.setContentTitle(message.data["text"])
            .setContentText(message.data["content_text"])
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setSmallIcon(androidx.appcompat.R.drawable.abc_btn_check_to_on_mtrl_015)

        manager.notify(0, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAGTAG", token)
    }
}