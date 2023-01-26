package com.turtleteam.turtle_database.database

import com.squareup.sqldelight.db.SqlDriver
import com.turtleteam.turtle_database.`typealias`.KotlinContext

expect class CommonSqlDriver(context: KotlinContext) {
    fun getDriver(): SqlDriver
}