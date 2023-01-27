package com.turtleteam.turtle_database.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.turtleteam.turtle_database.sqldelight.TurtleDatabase

actual class CommonSqlDriver {
    actual fun getDriver(): SqlDriver =
        NativeSqliteDriver(TurtleDatabase.Schema, "TurtleDatabase.db")
}