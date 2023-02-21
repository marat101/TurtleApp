package com.turtleteam.domain.usecases

import com.android.turtleapp.data.model.teachersandgroups.NamesList

interface GetListAndPinnedListUC {
    suspend fun execute(): NamesList
}