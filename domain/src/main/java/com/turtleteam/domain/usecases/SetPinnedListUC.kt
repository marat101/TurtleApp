package com.turtleteam.domain.usecases

import com.android.turtleapp.data.model.teachersandgroups.NamesList

interface SetPinnedListUC {
    fun execute(currentList: NamesList, item: String): NamesList
}