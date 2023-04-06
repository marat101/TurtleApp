package com.turtleteam.remote_database.utils

import android.content.Context
import com.turtleteam.remote_database.AppUpdate
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal interface UpdatePrefs {

    fun getSavedUpdateState(): AppUpdate

    fun savedUpdateState(state: AppUpdate.Success)
}

internal object UpdatePrefsImpl : KoinComponent, UpdatePrefs {

    private const val UPDATE_PREFS = "updatestate"
    private const val UPDATE = "update"

    private val json: Json by inject()

    override fun getSavedUpdateState(): AppUpdate {
        val context: Context by inject()
        val updateState =
            context.getSharedPreferences(UPDATE_PREFS, Context.MODE_PRIVATE).getString(UPDATE, "")
        return if (updateState.isNullOrBlank())
            AppUpdate.Empty
        else
            json.decodeFromString(
                AppUpdate.Success.serializer(),
                updateState
            )
    }

    override fun savedUpdateState(state: AppUpdate.Success) {
        val context: Context by inject()
        context.getSharedPreferences(UPDATE_PREFS, Context.MODE_PRIVATE).edit()
            .putString(UPDATE, json.encodeToString(AppUpdate.Success.serializer(), state)).apply()
    }
}