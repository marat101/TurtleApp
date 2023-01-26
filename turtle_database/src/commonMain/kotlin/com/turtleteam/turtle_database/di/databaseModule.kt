package com.turtleteam.turtle_database.di

import com.turtleteam.turtle_database.database.CommonSqlDriver
import com.turtleteam.turtle_database.database.GroupsScheduleDao
import com.turtleteam.turtle_database.database.TeachersScheduleDao
import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtle_database.`typealias`.KotlinContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        CommonSqlDriver(context = get())
    }
    single {
        TurtleDatabase(get<CommonSqlDriver>().getDriver())
    }
    single {
        GroupsScheduleDao(database = get())
    }
    single {
        TeachersScheduleDao(database = get())
    }
}