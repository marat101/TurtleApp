package com.turtleteam.domain.utils

import com.turtleteam.domain.model.NamesList

object SearchNames {

    fun filterList(query: String, list: NamesList): NamesList{
        if (query=="") return list
        val pinned = list.pinned.toMutableList()
        val groups = list.groups.toMutableList()
        val filteredItems = mutableListOf<String>()
        pinned.map { if (!it.lowercase().contains(query.lowercase())) filteredItems.add(it) }
        groups.map { if (!it.lowercase().contains(query.lowercase())) filteredItems.add(it) }
        pinned.removeAll(filteredItems)
        groups.removeAll(filteredItems)

        return NamesList(pinned, groups)
    }
}