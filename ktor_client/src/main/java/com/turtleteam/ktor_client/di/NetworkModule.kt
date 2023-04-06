package com.turtleteam.ktor_client.di

import com.turtleteam.ktor_client.api.ApiService
import com.turtleteam.ktor_client.api.Ktor
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import org.koin.dsl.module

val networkModule = module {

    single {
        HttpClient(OkHttp)
    }

    single<ApiService> {
        Ktor(get())
    }

}