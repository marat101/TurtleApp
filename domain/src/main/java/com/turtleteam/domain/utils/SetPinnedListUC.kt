package com.turtleteam.domain.utils

import com.turtleteam.domain.model.NamesList

interface SetPinnedListUC{
    fun execute(currentList: NamesList, item: String): NamesList
}