package com.turtleteam.ktor_client.api

import com.android.turtleapp.data.model.teachersandgroups.Groups
import com.turtleteam.domain.model.schedule.DaysList

interface ApiService {

    suspend fun getSchedule(name: String): DaysList

    suspend fun getList(): Groups
}