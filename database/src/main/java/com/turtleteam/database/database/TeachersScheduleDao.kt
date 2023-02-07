package com.turtleteam.database.database

//import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
//import com.turtleteam.turtledatabase.TeachersDaysList
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext

//class TeachersScheduleDao(database: TurtleDatabase) {
//
//    private val query = database.turtleDatabaseQueries
//
//    suspend fun getTeacherDaysList(name: String): TeachersDaysList =
//        withContext(Dispatchers.IO) {
//            query.selectTeacherScheduleByName(name).executeAsOne()
//        }
//
//    suspend fun saveTeacherDaysList(days: String, name: String) =
//        withContext(Dispatchers.IO) {
//            query.insertTeacher(days, name)
//        }
//
//    suspend fun getSavedScheduleList(): List<String> = withContext(Dispatchers.IO) {
//        query.getSavedTeachersList().executeAsList()
//    }

//}