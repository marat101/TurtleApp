package com.turtleteam.domain.usecases

import com.turtleteam.domain.model.teachersandgroups.NamesList

interface GetListAndPinnedListUC {
    suspend fun execute(): NamesList
}