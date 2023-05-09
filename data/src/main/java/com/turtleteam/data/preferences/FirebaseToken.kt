package com.turtleteam.data.preferences

import android.content.Context
import com.turtleteam.domain.repository.SubscriptionsRepository

class FirebaseToken(context: Context): SubscriptionsRepository {

    companion object{
        private const val PREFS_NAME = "firebasePrefs"
        private const val FCM_TOKEN = "fcmtoken"
    }

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveFCMToken(token: String) {
        prefs.edit().putString(FCM_TOKEN, token).apply()
    }

    override fun getToken(): String? = prefs.getString(FCM_TOKEN, "")
}