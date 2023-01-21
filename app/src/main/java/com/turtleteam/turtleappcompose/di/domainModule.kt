package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.groups.*
import org.koin.dsl.module

val domainModule = module {

    // Группы
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
    factory {
        GetGroupsAndPinnedListUseCase(repository = get())
    }
    factory {
        SetPinnedListUseCase(repository = get())
    }

    // Преподаватели

}