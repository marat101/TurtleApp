package com.turtleteam.remote_database.firestore_model

import kotlinx.serialization.Serializable

@Serializable
internal data class Number(
    val integerValue: String
)