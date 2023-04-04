package com.turtleteam.remote_database

import org.koin.dsl.module

val firestoreModule = module {
    single<UpdateService>{
        UpdateServiceImpl()
    }
}