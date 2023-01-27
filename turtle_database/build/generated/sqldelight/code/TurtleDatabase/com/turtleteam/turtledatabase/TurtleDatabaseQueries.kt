package com.turtleteam.turtledatabase

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String
import kotlin.Unit

public interface TurtleDatabaseQueries : Transacter {
  public fun <T : Any> selectGroupScheduleByName(name: String, mapper: (days: String,
      name: String) -> T): Query<T>

  public fun selectGroupScheduleByName(name: String): Query<GroupsDaysList>

  public fun getSavedGroupsList(): Query<String>

  public fun <T : Any> selectTeacherScheduleByName(name: String, mapper: (days: String,
      name: String) -> T): Query<T>

  public fun selectTeacherScheduleByName(name: String): Query<TeachersDaysList>

  public fun getSavedTeachersList(): Query<String>

  public fun insertGroup(days: String, name: String): Unit

  public fun insertTeacher(days: String, name: String): Unit
}
