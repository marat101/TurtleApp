package com.turtleteam.turtleappcompose.di

import com.android.turtleapp.data.local.assets.AssetsService
import com.android.turtleapp.data.local.database.TurtleRoomDatabase
import com.turtleteam.data.api.ApiService
import com.turtleteam.data.api.Ktor
import com.turtleteam.data.preferences.PreferencesStore
import org.koin.dsl.module

val dataModule = module {
    single {
        TurtleRoomDatabase.create(context = get())
    }
    single {
        PreferencesStore(context = get())
    }
    single {
        get<TurtleRoomDatabase>().groupsScheduleDao()
    }
    single {
        get<TurtleRoomDatabase>().teachersScheduleDao()
    }
    single {
        AssetsService(context = get())
    }
    single<ApiService> {
        Ktor()
    }
}