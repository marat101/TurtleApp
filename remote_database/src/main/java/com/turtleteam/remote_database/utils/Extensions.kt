package com.turtleteam.remote_database.utils

import com.turtleteam.remote_database.AppUpdate
import com.turtleteam.remote_database.firestore_model.UpdateFirestore


internal fun UpdateFirestore.toUpdate(): AppUpdate.Success =
    AppUpdate.Success(
    fields.link.stringValue,
    fields.number.integerValue.toInt()
)

