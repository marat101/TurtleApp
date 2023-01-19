package com.android.turtleapp.data.model.teachersandgroups

import kotlinx.serialization.Serializable

@Serializable
data class Groups(
    val group: List<String>,
    val teacher: List<String>
)