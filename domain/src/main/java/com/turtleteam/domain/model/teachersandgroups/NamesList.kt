package com.turtleteam.domain.model.teachersandgroups

import kotlinx.serialization.Serializable

@Serializable
data class NamesList(
    val pinned: List<String>,
    val groups: List<String> = emptyList()
){
    companion object{
        val empty = NamesList(emptyList(), emptyList())
    }
}