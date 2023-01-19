package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.GetGroupsListUseCase
import org.koin.dsl.module

val domainModule = module{

    factory {
        GetGroupsListUseCase(repository = get())
    }
}