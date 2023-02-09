package com.turtleteam.turtleappcompose.di

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.data.repository.GroupsRepositoryImpl
import com.turtleteam.data.repository.ManageSettingsImpl
import com.turtleteam.data.repository.TeachersRepositoryImpl
import com.turtleteam.domain.repository.GroupsRepository
import com.turtleteam.domain.repository.ManageSettings
import com.turtleteam.turtleappcompose.WidgetActivityViewModel
import com.turtleteam.turtleappcompose.widget.WidgetDataManage
import org.koin.androidx.viewmodel.dsl.viewModel
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
    viewModel {
        WidgetActivityViewModel(
            getGroups = get(),
            getTeachers = get(),
            getSch = get(),
            setter = WidgetDataManage.SetData.Base(context = get())
        )
    }
}