package com.turtleteam.turtle_database.di

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.turtleteam.turtle_database.TurtleDatabase
import com.turtleteam.turtle_database.dao.*
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> {
        AndroidSqliteDriver(TurtleDatabase.Schema, get(), "TurtleDatabase.db")
    }
    single { TurtleDatabase(get()) }

    single<GroupsScheduleDao> { GroupsScheduleDaoImpl(database = get()) }
    single<TeachersScheduleDao> { TeachersScheduleDaoImpl(database = get()) }
    single<ScheduleWidgetDao> { ScheduleWidgetDaoImpl(database = get()) }
}