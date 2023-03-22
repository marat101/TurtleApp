package com.turtleteam.domain.usecases

interface GetSavedNamesListUC {
    suspend fun execute(): List<String>?
}