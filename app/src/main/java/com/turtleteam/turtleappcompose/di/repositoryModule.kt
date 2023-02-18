package com.turtleteam.turtleappcompose.di

import com.turtleteam.data.repository.GroupsRepositoryImpl
import com.turtleteam.data.repository.ManageSettingsImpl
import com.turtleteam.data.repository.TeachersRepositoryImpl
import com.turtleteam.domain.repository.ManageSettings
import com.turtleteam.domain.repository.ScheduleRepository
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single<ScheduleRepository>(named("ScheduleGroupsRepository")) {
        GroupsRepositoryImpl(
            groupsScheduleDao = get(),
            apiService = get(),
            preferencesStore = get(),
        )
    } bind ScheduleRepository::class
    single<ScheduleRepository>(named("ScheduleTeachersRepository")) {
        TeachersRepositoryImpl(
            teachersScheduleDao = get(),
            apiService = get(),
            preferencesStore = get()
        )
    } bind ScheduleRepository::class

    single<ManageSettings> {
        ManageSettingsImpl(preferencesStore = get())
    }
}