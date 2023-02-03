package com.turtleteam.turtleappcompose.di

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.data.repository.GroupsRepositoryImpl
import com.turtleteam.data.repository.ManageSettingsImpl
import com.turtleteam.data.repository.TeachersRepositoryImpl
import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.repository.ManageSettings
import org.koin.dsl.module

val repositoryModule = module {
    single<GroupsRepository> {
        GroupsRepositoryImpl(
            groupsScheduleDao = get(),
            apiService = get(),
            preferencesStore = get(),
        )
    }
    single<TeachersRepository> {
        TeachersRepositoryImpl(
            teachersScheduleDao = get(),
            apiService = get(),
            preferencesStore = get()
        )
    }
    single<ManageSettings> {
        ManageSettingsImpl(preferencesStore = get())
    }
}