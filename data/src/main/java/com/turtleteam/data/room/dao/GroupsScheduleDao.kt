package com.android.turtleapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.turtleapp.data.local.entity.GroupsDaysList

@Dao
interface GroupsScheduleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: GroupsDaysList)

    @Query("SELECT*FROM GroupsDaysList WHERE name = :groupName")
    suspend fun getScheduleByName(groupName: String): GroupsDaysList

    @Query("SELECT name FROM GroupsDaysList")
    suspend fun getScheduleList(): List<String>
}