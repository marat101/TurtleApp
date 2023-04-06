package com.turtleteam.remote_database

import android.content.Context
import android.content.pm.PackageManager
import com.turtleteam.remote_database.firestore_model.UpdateFirestore
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

internal class UpdateServiceImpl(private val ktorClient: HttpClient) : KoinComponent,
    UpdateService, UpdateImpl() {

    private val json: Json by inject()

    companion object {
        private const val FIRESTORE_URL =
            "https://firestore.googleapis.com/v1beta1/projects/turtle-app-3f0e6/databases/(default)/documents/"
    }

    override suspend fun getLatestVersion() {
        //TODO сохранение обновы, обработка исключений, сравнение текущей версии приложения с версией в файрбейз

        try {
            val request = ktorClient.get(FIRESTORE_URL + "test/update")
            val response = json.decodeFromString(UpdateFirestore.serializer(), request.body()).toUpdate()
            val context: Context by inject()
            val versionNCode = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_META_DATA).versionCode

            update.emit(response.copy(isUpdateAvaible = response.number > versionNCode))
        } catch (e: Exception) {
            update.emit(AppUpdate.Error(e))
        }
    }
}