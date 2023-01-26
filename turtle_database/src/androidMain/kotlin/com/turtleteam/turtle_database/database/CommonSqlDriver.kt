package com.turtleteam.turtle_database.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.turtleteam.turtle_database.sqldelight.TurtleDatabase
import com.turtleteam.turtle_database.`typealias`.KotlinContext

actual class CommonSqlDriver actual constructor(private val context: KotlinContext) {
    actual fun getDriver(): SqlDriver =
        AndroidSqliteDriver(TurtleDatabase.Schema, context, "TurtleDatabase.db")
}