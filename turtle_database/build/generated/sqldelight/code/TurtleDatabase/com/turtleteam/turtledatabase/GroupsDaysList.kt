package com.turtleteam.turtledatabase

public data class GroupsDaysList(
  public val days: String,
  public val name: String
) {
  public override fun toString(): String = """
  |GroupsDaysList [
  |  days: $days
  |  name: $name
  |]
  """.trimMargin()
}