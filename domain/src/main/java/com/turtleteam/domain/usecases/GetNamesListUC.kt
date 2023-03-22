package com.turtleteam.domain.usecases

interface GetNamesListUC {
    suspend fun execute(): List<String>
}