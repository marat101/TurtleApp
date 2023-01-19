package com.android.turtleapp.data.model.teachersandgroups

import kotlinx.serialization.Serializable

@Serializable
data class NamesList(
    val pinned: List<String>,
    val groups: List<String>
)
