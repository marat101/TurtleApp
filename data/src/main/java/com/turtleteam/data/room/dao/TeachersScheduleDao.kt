package com.android.turtleapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.turtleapp.data.local.entity.TeachersDaysList

@Dao
interface TeachersScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: TeachersDaysList)

    @Query("SELECT*FROM TeachersDaysList WHERE name = :teachersName")
    suspend fun getScheduleByName(teachersName: String): TeachersDaysList

    @Query("SELECT name FROM TeachersDaysList")
    suspend fun getScheduleList(): List<String>
}