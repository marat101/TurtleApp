package com.turtleteam.data.preferences

import android.content.Context

class UserSettings(context: Context) {

    companion object {
        private const val PREFS_NAME = "settings_prefs"

        private const val THEME_NAME = "theme"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveTheme(theme: Boolean) = preferences.edit().putBoolean(THEME_NAME, theme).apply()

    fun setSavedTheme(): Boolean = preferences.getBoolean(THEME_NAME, false)

}