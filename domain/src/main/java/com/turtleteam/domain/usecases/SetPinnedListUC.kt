package com.turtleteam.domain.usecases

import com.turtleteam.domain.model.teachersandgroups.NamesList

interface SetPinnedListUC {
    fun execute(currentList: NamesList, item: String): NamesList
}