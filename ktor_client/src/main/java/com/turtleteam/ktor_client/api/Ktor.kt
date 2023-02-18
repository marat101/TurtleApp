package com.turtleteam.ktor_client.api

import android.util.Log
import com.android.turtleapp.data.model.teachersandgroups.Groups
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ktor_client.exceptions.HttpException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal class Ktor(private val ktorClient: HttpClient) : ApiService {

    companion object {
        private const val BASE_URL = "http://45.155.207.232:8080/api/v2/"
    }

    override suspend fun getSchedule(name: String): DaysList {
        val response = ktorClient.get(BASE_URL + "schedule/$name")
        checkResponse(response)
        return Json.decodeFromString(response.body())
    }

    override suspend fun getList(): Groups {
        val response = ktorClient.get(BASE_URL + "schedule/list")
        checkResponse(response)
        return Json.decodeFromString(response.body())
    }

    private fun checkResponse(response: HttpResponse){
        if (response.status != HttpStatusCode.OK) throw HttpException(response.status.description, response.status.value)
    }
}