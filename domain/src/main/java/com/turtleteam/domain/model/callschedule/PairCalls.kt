package com.turtleteam.domain.model.callschedule

import kotlinx.serialization.Serializable

@Serializable
data class PairCalls(
    val end: String,
    val number: Int,
    val start: String
)