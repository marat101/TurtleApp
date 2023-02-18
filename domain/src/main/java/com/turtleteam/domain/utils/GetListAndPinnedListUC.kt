package com.turtleteam.domain.utils

import com.android.turtleapp.data.model.teachersandgroups.NamesList

interface GetListAndPinnedListUC{
    suspend fun execute(): NamesList
}