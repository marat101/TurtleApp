package com.android.turtleapp.data.model.schedule

import kotlinx.serialization.Serializable

@Serializable
data class Pair(
    val auditoria: String,
    val corpus: String,
    val doctrine: String,
    val end: String,
    val number: Int,
    val start: String,
    val teacher: String,
    val warn: String
)