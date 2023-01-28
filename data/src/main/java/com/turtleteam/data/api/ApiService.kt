package com.turtleteam.data.api

import com.android.turtleapp.data.local.entity.GroupsDaysList
import com.android.turtleapp.data.model.teachersandgroups.Groups
import com.turtleteam.data.entity.TeachersDaysList

interface ApiService {

    suspend fun getGroupsScheduleList(group: String): GroupsDaysList

    suspend fun getTeachersSchedulelist(teacher: String): TeachersDaysList

    suspend fun getGroupsList(): Groups
}