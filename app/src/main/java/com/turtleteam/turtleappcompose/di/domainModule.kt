package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.groups.GetGroupsListUseCase
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.GetSavedGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.SaveGroupScheduleUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetGroupsListUseCase(repository = get())
    }
    factory {
        GetGroupScheduleUseCase(repository = get())
    }
    factory {
        GetSavedGroupScheduleUseCase(repository = get())
    }
    factory {
        SaveGroupScheduleUseCase(repository = get())
    }
}