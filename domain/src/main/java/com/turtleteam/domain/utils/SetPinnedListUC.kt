package com.turtleteam.domain.utils

import com.android.turtleapp.data.model.teachersandgroups.NamesList

interface SetPinnedListUC {
    fun execute(currentList: NamesList, item: String): NamesList
}