package com.turtleteam.domain.model.callschedule

import kotlinx.serialization.Serializable

@Serializable
data class Calls(
    val calls: List<PairCalls>,
    val type: String
)