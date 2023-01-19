package com.android.turtleapp.data.model.callschedule

import kotlinx.serialization.Serializable

@Serializable
data class CallsItem(
    val schedule: List<String>
)