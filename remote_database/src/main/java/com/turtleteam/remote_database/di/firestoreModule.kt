package com.turtleteam.remote_database.di

import com.turtleteam.remote_database.Update
import com.turtleteam.remote_database.UpdateService
import com.turtleteam.remote_database.UpdateServiceImpl
import com.turtleteam.remote_database.utils.UpdatePrefs
import com.turtleteam.remote_database.utils.UpdatePrefsImpl
import org.koin.dsl.module

val firestoreModule = module {
    single<UpdateService> { UpdateServiceImpl(get(), get()) }
    single<UpdatePrefs> { UpdatePrefsImpl }
    single<Update> { get<UpdateService>() }
}