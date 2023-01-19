package com.android.turtleapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.turtleapp.data.local.converter.Converters
import com.android.turtleapp.data.local.dao.GroupsScheduleDao
import com.android.turtleapp.data.local.dao.TeachersScheduleDao
import com.android.turtleapp.data.local.entity.GroupsDaysList
import com.android.turtleapp.data.local.entity.TeachersDaysList

@Database(entities = [GroupsDaysList::class, TeachersDaysList::class], version = 3)
@TypeConverters(Converters::class)
abstract class TurtleRoomDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "turtledb"

        fun create(context: Context): TurtleRoomDatabase = Room.databaseBuilder(
            context,
            TurtleRoomDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    abstract fun groupsScheduleDao(): GroupsScheduleDao

    abstract fun teachersScheduleDao(): TeachersScheduleDao
}
