package com.android.turtleapp.data.model.schedule

import kotlinx.serialization.Serializable

@Serializable
data class PairsList(
    val apair: List<Pair>,
    val time: String,
    val isoDateStart: String,
    val isoDateEnd: String
)