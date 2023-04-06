package com.turtleteam.data.di

import com.android.turtleapp.data.local.assets.AssetsService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.repository.Assets
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        PreferencesStore(context = get())
    }

    single<Assets> {
        AssetsService(context = get())
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
}