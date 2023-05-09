package com.turtleteam.data.di

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.android.turtleapp.data.local.assets.AssetsService
import com.turtleteam.data.preferences.FirebaseToken
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.repository.Assets
import com.turtleteam.domain.repository.SubscriptionsRepository
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

@SuppressLint("HardwareIds")
val dataModule = module {

    factory<String>(named(Settings.Secure.ANDROID_ID)) {
        Settings.Secure.getString(get<Context>().contentResolver, Settings.Secure.ANDROID_ID)
    }

    single {
        PreferencesStore(context = get())
    }

    single<Assets> {
        AssetsService(context = get())
    }

    single<SubscriptionsRepository> {
        FirebaseToken(get())
    }

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
}