package com.turtleteam.turtleappcompose.di

import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.data.repository.GroupsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<GroupsRepository> {
        GroupsRepositoryImpl(
            groupsScheduleDao = get(),
            apiService = get(),
            preferencesStore = get()
        )
    }
}