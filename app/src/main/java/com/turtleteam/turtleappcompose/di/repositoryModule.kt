package com.turtleteam.turtleappcompose.di

import com.turtleteam.data.repository.GroupsRepositoryImpl
import com.turtleteam.data.repository.ManageSettingsImpl
import com.turtleteam.data.repository.TeachersRepositoryImpl
import com.turtleteam.domain.repository.ManageSettings
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.domain.repository.WidgetRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<ScheduleRepository>(named("groups")) {
        GroupsRepositoryImpl(
            groupsScheduleDao = get(),
            apiService = get(),
            preferencesStore = get(),
        )
    }
    single<ScheduleRepository>(named("teachers")) {
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
