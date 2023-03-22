package com.turtleteam.domain.usecases

interface GetPinnedListUC {
    suspend fun execute(): List<String>
}