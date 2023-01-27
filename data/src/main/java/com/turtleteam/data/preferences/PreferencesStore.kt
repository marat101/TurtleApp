package com.turtleteam.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.turtleteam.data.converter.Converters

class PreferencesStore(context: Context?) {

    companion object {
        private const val THEME_NAME = "theme"
        private const val HINT = "hint"

        private const val SAVED_URL = "SAVEDURL"

        private const val WIDGET_SCHEDULE_NAME = "request"
        private const val WIDGET_IDS = "ids"

        const val SELECTED_ID = "ГРУППА"

        const val SELECTED_ID2 = "ПРЕПОДАВАТЕЛЬ"
        const val PINNED_GROUPS = "PINNEDGROUP"

        const val PINNED_TEACHERS = "PINNEDTEACHER"
        const val LAST_GROUP = "LASTGROUP"
    }

    private var preferences: SharedPreferences =
        context!!.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)

    fun saveSelectedItem(id: String, str: String) = preferences.edit().putString(id, str).apply()

    fun getSavedItem(id: String): String? = preferences.getString(id, id)

    fun saveTheme(theme: Boolean) = preferences.edit().putBoolean(THEME_NAME, theme).apply()

    fun setSavedTheme(): Boolean = preferences.getBoolean(THEME_NAME, false)

    fun savePinnedList(key: String, list: List<String>) =
        preferences.edit().putString(key, Converters.stringToGson(list)).apply()

    fun getPinnedList(key: String): List<String> =
        Converters.gsonToString(preferences.getString(key, null)!!)

    fun showHint(): Boolean = preferences.getBoolean(HINT, true)

    fun hideHint() = preferences.edit().putBoolean(HINT, false).apply()

    fun saveUrl(url: String) = preferences.edit().putString(SAVED_URL, url).apply()

    fun getSavedUrl(): String? = preferences.getString(
        SAVED_URL,
        "https://docs.google.com/spreadsheets/d/16d1IV9rg0IKHMEpjUZoW_mn0js-H-kTl/edit#gid=473945966"
    )

    fun getSavedWidgetScheduleName(): String =
        preferences.getString(WIDGET_SCHEDULE_NAME, "Поиск").toString()

    fun saveWidgetScheduleName(value: String) =
        preferences.edit().putString(WIDGET_SCHEDULE_NAME, value).apply()

    fun getWidgetId(id: Int) = preferences.edit().putInt(WIDGET_IDS, id).apply()

    fun setWidgetId(): Int = preferences.getInt(WIDGET_IDS, 0)

    fun getLastTargetGroup():String = preferences.getString(LAST_GROUP,"Группы")!!
    fun setLastTargetGroup(group:String) = preferences.edit().putString(LAST_GROUP,group).apply()
}