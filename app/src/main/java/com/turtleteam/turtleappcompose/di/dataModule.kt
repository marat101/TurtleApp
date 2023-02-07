package com.turtleteam.turtleappcompose.di

import com.android.turtleapp.data.local.assets.AssetsService
import com.turtleteam.ktor_client.api.ApiService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.repository.Assets
import org.koin.dsl.module

val dataModule = module {
    single {
        PreferencesStore(context = get())
    }
    single<Assets> {
        AssetsService(context = get())
    }
}