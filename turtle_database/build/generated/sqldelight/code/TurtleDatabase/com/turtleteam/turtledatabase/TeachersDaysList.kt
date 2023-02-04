package com.turtleteam.turtledatabase

import kotlin.String

public data class TeachersDaysList(
  public val days: String,
  public val name: String
) {
  public override fun toString(): String = """
  |TeachersDaysList [
  |  days: $days
  |  name: $name
  |]
  """.trimMargin()
}
