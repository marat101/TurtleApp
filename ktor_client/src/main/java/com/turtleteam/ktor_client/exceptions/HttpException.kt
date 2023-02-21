package com.turtleteam.ktor_client.exceptions

data class HttpException(
    override val message: String,
    val code: Int
) : Exception(message)
