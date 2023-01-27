package com.turtleteam.turtle_database.sqldelight

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import com.turtleteam.turtle_database.sqldelight.turtledatabase.newInstance
import com.turtleteam.turtle_database.sqldelight.turtledatabase.schema
import com.turtleteam.turtledatabase.TurtleDatabaseQueries

public interface TurtleDatabase : Transacter {
  public val turtleDatabaseQueries: TurtleDatabaseQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = TurtleDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): TurtleDatabase =
        TurtleDatabase::class.newInstance(driver)
  }
}
