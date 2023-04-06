package com.turtleteam.remote_database

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

interface Update {
    val state: SharedFlow<AppUpdate>
}

internal abstract class UpdateImpl : Update, KoinComponent {

    protected val update = MutableSharedFlow<AppUpdate>(replay = 1)
    override val state = update.asSharedFlow()
}

sealed interface AppUpdate {

    @Serializable
    data class Success(
        val link: String = "",
        val number: Int = 0,
        val isUpdateAvaible: Boolean = false
    ) : AppUpdate

    data class Error(val error: Exception) : AppUpdate

    object Empty : AppUpdate
}