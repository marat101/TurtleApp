package com.turtleteam.domain.utils

import com.turtleteam.domain.model.NamesList

interface GetListAndPinnedListUC{
    suspend fun execute(): NamesList
}