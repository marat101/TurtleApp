package com.turtleteam.remote_database

import com.turtleteam.remote_database.utils.UpdatePrefs
import com.turtleteam.remote_database.utils.UpdatePrefsImpl
import org.koin.dsl.module

val firestoreModule = module {
    single<UpdateService> { UpdateServiceImpl(get()) }
    single<UpdatePrefs> { UpdatePrefsImpl }
    single<Update> { get<UpdateService>() }
}