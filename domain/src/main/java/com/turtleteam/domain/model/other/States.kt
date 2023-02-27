package com.turtleteam.domain.model.other

sealed class States<out T> {

    data class Success<out T>(val value: T) : States<T>()

    object Error : States<Nothing>()

    object NotFoundError : States<Nothing>()

    object Loading : States<Nothing>()
}