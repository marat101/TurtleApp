package com.turtleteam.remote_database

import android.app.Application
import android.content.pm.PackageManager
import com.turtleteam.remote_database.firestore_model.UpdateFirestore
import com.turtleteam.remote_database.utils.UpdatePrefs
import com.turtleteam.remote_database.utils.toUpdate
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface UpdateService : Update {

    suspend fun getLatestVersion()
}

internal class UpdateServiceImpl(
    private val ktorClient: HttpClient,
    private val prefs: UpdatePrefs
) : KoinComponent,
    UpdateService, UpdateImpl() {

    private val json: Json by inject()

    companion object {
        private const val FIRESTORE_URL =
            "https://firestore.googleapis.com/v1beta1/projects/turtle-app-3f0e6/databases/(default)/documents/"
    }

    override suspend fun getLatestVersion() {
        try {
            val request = ktorClient.get(FIRESTORE_URL + "test/update")
            val response =
                json.decodeFromString(UpdateFirestore.serializer(), request.body()).toUpdate()
            val context: Application by inject()
            val versionNCode = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            ).versionCode
            val state = response.copy(isUpdateAvaible = response.number > versionNCode)

            update.emit(state)
            prefs.savedUpdateState(state)
        } catch (e: Exception) {
            update.emit(prefs.getSavedUpdateState())
        }
    }
}