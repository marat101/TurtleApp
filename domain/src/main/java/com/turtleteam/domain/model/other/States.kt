package com.turtleteam.domain.model.other

sealed class States<out T> {

    data class Success<out T>(val value: T) : States<T>()

    data class Error(val error: Throwable? = null) : States<Nothing>()

    object ConnectionError : States<Nothing>()

    object NotFoundError : States<Nothing>()

    object Loading : States<Nothing>()
}