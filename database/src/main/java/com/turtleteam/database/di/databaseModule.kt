package com.turtleteam.turtle_database.di

//import com.turtleteam.database.database.CommonSqlDriver
//import com.turtleteam.database.database.GroupsScheduleDao
//import com.turtleteam.database.database.TeachersScheduleDao
//import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
//import org.koin.dsl.module
//
//val databaseModule = module {
//    single {
//        CommonSqlDriver(context = get())
//    }
//    single {
//        TurtleDatabase(get<CommonSqlDriver>().getDriver())
//    }
//    single {
//        GroupsScheduleDao(database = get())
//    }
//    single {
//        TeachersScheduleDao(database = get())
//    }
//}