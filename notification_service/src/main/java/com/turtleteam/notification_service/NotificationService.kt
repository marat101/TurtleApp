package com.turtleteam.notification_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {

    companion object {
        const val CHANNEL_ID = "Тестовые сообщения"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        setMessage(message)
    }

    override fun handleIntent(intent: Intent?) {
        val data = intent?.extras as Bundle
        val message = RemoteMessage(data)
        setMessage(message)
        super.handleIntent(intent)
    }

    fun setMessage(message: RemoteMessage) {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = CHANNEL_ID
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setSmallIcon(R.drawable.turtle_right_4)
            .setStyle(
                NotificationCompat.BigTextStyle().bigText(message.notification?.body)
                    .setSummaryText("")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        Log.e("TAGTAG", message.data.toString())
        Log.e("TAGTAG", message.messageId.toString())
        Log.e("TAGTAG", message.notification?.body.toString())

        manager.notify(0, builder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("TAGTAG", token)
        Log.e("TAGTAG", Build.BRAND)
//        val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        clipboard.setPrimaryClip(ClipData.newPlainText(token, token))
    }
}