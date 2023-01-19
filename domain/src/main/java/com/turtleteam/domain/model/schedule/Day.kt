package com.android.turtleapp.data.model.schedule

import kotlinx.serialization.Serializable

@Serializable
data class Day(
    val apairs: List<PairsList>,
    val day: String
)