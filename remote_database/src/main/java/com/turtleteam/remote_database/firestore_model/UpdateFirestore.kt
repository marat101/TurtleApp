package com.turtleteam.remote_database.firestore_model

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateFirestore(
    val createTime: String,
    val fields: Fields,
    val name: String,
    val updateTime: String
)