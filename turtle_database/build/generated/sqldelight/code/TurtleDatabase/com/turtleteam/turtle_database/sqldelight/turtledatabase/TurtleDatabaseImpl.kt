package com.turtleteam.turtle_database.sqldelight.turtledatabase

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtledatabase.GroupsDaysList
import com.turtleteam.turtledatabase.TeachersDaysList
import com.turtleteam.turtledatabase.TurtleDatabaseQueries
import kotlin.reflect.KClass

internal val KClass<TurtleDatabase>.schema: SqlDriver.Schema
    get() = TurtleDatabaseImpl.Schema

internal fun KClass<TurtleDatabase>.newInstance(driver: SqlDriver): TurtleDatabase =
    TurtleDatabaseImpl(driver)

private class TurtleDatabaseImpl(
    driver: SqlDriver
) : TransacterImpl(driver), TurtleDatabase {
    public override val turtleDatabaseQueries: TurtleDatabaseQueriesImpl =
        TurtleDatabaseQueriesImpl(this, driver)

    public object Schema : SqlDriver.Schema {
        public override val version: Int
            get() = 1

        public override fun create(driver: SqlDriver): Unit {
            driver.execute(
                null, """
          |CREATE TABLE GroupsDaysList (
          |    days TEXT NOT NULL,
          |    name TEXT NOT NULL UNIQUE PRIMARY KEY
          |)
          """.trimMargin(), 0
            )
            driver.execute(
                null, """
          |CREATE TABLE TeachersDaysList (
          |    days TEXT NOT NULL,
          |    name TEXT NOT NULL UNIQUE PRIMARY KEY
          |)
          """.trimMargin(), 0
            )
        }

        public override fun migrate(
            driver: SqlDriver,
            oldVersion: Int,
            newVersion: Int
        ): Unit {
        }
    }
}

private class TurtleDatabaseQueriesImpl(
    private val database: TurtleDatabaseImpl,
    private val driver: SqlDriver
) : TransacterImpl(driver), TurtleDatabaseQueries {
    internal val selectGroupScheduleByName: MutableList<Query<*>> = copyOnWriteList()

    internal val getSavedGroupsList: MutableList<Query<*>> = copyOnWriteList()

    internal val selectTeacherScheduleByName: MutableList<Query<*>> = copyOnWriteList()

    internal val getSavedTeachersList: MutableList<Query<*>> = copyOnWriteList()

    public override fun <T : Any> selectGroupScheduleByName(
        name: String, mapper: (
            days: String,
            name: String
        ) -> T
    ): Query<T> = SelectGroupScheduleByNameQuery(name) { cursor ->
        mapper(
            cursor.getString(0)!!,
            cursor.getString(1)!!
        )
    }

    public override fun selectGroupScheduleByName(name: String): Query<GroupsDaysList> =
        selectGroupScheduleByName(name) { days, name_ ->
            GroupsDaysList(
                days,
                name_
            )
        }

    public override fun getSavedGroupsList(): Query<String> = Query(
        -1999650749, getSavedGroupsList,
        driver, "TurtleDatabase.sq", "getSavedGroupsList", "SELECT name FROM GroupsDaysList"
    ) { cursor ->
        cursor.getString(0)!!
    }

    public override fun <T : Any> selectTeacherScheduleByName(
        name: String, mapper: (
            days: String,
            name: String
        ) -> T
    ): Query<T> = SelectTeacherScheduleByNameQuery(name) { cursor ->
        mapper(
            cursor.getString(0)!!,
            cursor.getString(1)!!
        )
    }

    public override fun selectTeacherScheduleByName(name: String): Query<TeachersDaysList> =
        selectTeacherScheduleByName(name) { days, name_ ->
            TeachersDaysList(
                days,
                name_
            )
        }

    public override fun getSavedTeachersList(): Query<String> = Query(
        -1121474432,
        getSavedTeachersList, driver, "TurtleDatabase.sq", "getSavedTeachersList",
        "SELECT name FROM TeachersDaysList"
    ) { cursor ->
        cursor.getString(0)!!
    }

    public override fun insertGroup(days: String, name: String): Unit {
        driver.execute(
            497066822, """
    |INSERT OR REPLACE INTO GroupsDaysList(
    |    days,
    |    name
    |)
    |VALUES(?,?)
    """.trimMargin(), 2
        ) {
            bindString(1, days)
            bindString(2, name)
        }
        notifyQueries(497066822, {
            database.turtleDatabaseQueries.getSavedGroupsList +
                    database.turtleDatabaseQueries.selectGroupScheduleByName
        })
    }

    public override fun insertTeacher(days: String, name: String): Unit {
        driver.execute(
            -793156887, """
    |INSERT OR REPLACE INTO TeachersDaysList(
    |    days,
    |    name
    |)
    |VALUES(?,?)
    """.trimMargin(), 2
        ) {
            bindString(1, days)
            bindString(2, name)
        }
        notifyQueries(-793156887, {
            database.turtleDatabaseQueries.getSavedTeachersList +
                    database.turtleDatabaseQueries.selectTeacherScheduleByName
        })
    }

    private inner class SelectGroupScheduleByNameQuery<out T : Any>(
        public val name: String,
        mapper: (SqlCursor) -> T
    ) : Query<T>(selectGroupScheduleByName, mapper) {
        public override fun execute(): SqlCursor = driver.executeQuery(
            1157402300,
            """SELECT * FROM GroupsDaysList WHERE name = ?""", 1
        ) {
            bindString(1, name)
        }

        public override fun toString(): String = "TurtleDatabase.sq:selectGroupScheduleByName"
    }

    private inner class SelectTeacherScheduleByNameQuery<out T : Any>(
        public val name: String,
        mapper: (SqlCursor) -> T
    ) : Query<T>(selectTeacherScheduleByName, mapper) {
        public override fun execute(): SqlCursor = driver.executeQuery(
            1171845599,
            """SELECT * FROM TeachersDaysList WHERE name = ?""", 1
        ) {
            bindString(1, name)
        }

        public override fun toString(): String = "TurtleDatabase.sq:selectTeacherScheduleByName"
    }
}
