package com.turtleteam.data.api

import com.android.turtleapp.data.local.entity.GroupsDaysList
import com.android.turtleapp.data.local.entity.TeachersDaysList
import com.android.turtleapp.data.model.teachersandgroups.Groups
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Ktor : ApiService {

    companion object {
        private const val BASE_URL = "http://45.155.207.232:8080/api/v2/"

        private val ktorClient = HttpClient(OkHttp)
    }

    override suspend fun getGroupsScheduleList(group: String): GroupsDaysList {
        val response = ktorClient.get(BASE_URL + "schedule/$group")
        return Json.decodeFromString(response.body())
    }

    override suspend fun getTeachersSchedulelist(teacher: String): TeachersDaysList {
        val response = ktorClient.get(BASE_URL + "schedule/$teacher")
        return Json.decodeFromString(response.body())
    }

    override suspend fun getGroupsList(): Groups {
        val response = ktorClient.get(BASE_URL + "schedule/list")
        return Json.decodeFromString(response.body())
    }
}