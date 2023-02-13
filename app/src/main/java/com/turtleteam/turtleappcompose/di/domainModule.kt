package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.groups.*
import com.turtleteam.domain.usecases.teachers.*
import com.turtleteam.domain.usecases.usersettings.*
import com.turtleteam.domain.utils.*
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val domainModule = module {

    // Группы
    factory(named("GroupUseCase")) {
        GetGroupsListUseCase(repository = get())
    }
    factory(named("GroupUseCase")) {
        GetGroupScheduleUseCase(repository = get())
    } bind GetScheduleUC::class

    factory(named("GroupUseCase")) {
        GetSavedGroupScheduleUseCase(repository = get())
    } bind GetSavedScheduleUC::class

    factory(named("GroupUseCase")) {
        SaveGroupScheduleUseCase(repository = get())
    } bind SaveScheduleUC::class

    factory(named("GroupUseCase")) {
        GetGroupsAndPinnedListUseCase(repository = get())
    } bind GetListAndPinnedListUC::class

    factory(named("GroupUseCase")) {
        SetPinnedGroupsListUseCase(repository = get())
    } bind SetPinnedListUC::class

    factory(named("GroupUseCase")) {
        GetLastTargetGroupUseCase(repository = get())
    } bind GetLastTargetUC::class
    factory(named("GroupUseCase")) {
        SetLastTargetGroupUseCase(repository = get())
    } bind SetLastTargetUC::class

    // Преподаватели
    factory(named("TeacherUseCase")) {
        GetTeachersListUseCase(repository = get())
    }
    factory(named("TeacherUseCase")) {
        GetTeacherScheduleUseCase(repository = get())
    } bind GetScheduleUC::class
    factory(named("TeacherUseCase")) {
        GetSavedTeacherScheduleUseCase(repository = get())
    } bind GetSavedScheduleUC::class
    factory(named("TeacherUseCase")) {
        SaveTeacherScheduleUseCase(repository = get())
    } bind SaveScheduleUC::class
    factory(named("TeacherUseCase")) {
        GetTeachersAndPinnedListUseCase(repository = get())
    } bind GetListAndPinnedListUC::class
    factory(named("TeacherUseCase")) {
        SetPinnedTeachersListUseCase(repository = get())
    } bind SetPinnedListUC::class
    factory(named("TeacherUseCase")) {
        GetLastTargetTeacherUseCase(repository = get())
    } bind GetLastTargetUC::class
    factory(named("TeacherUseCase")) {
        SetLastTargetTeacherUseCase(repository = get())
    } bind SetLastTargetUC::class
    //Utils
    factory {
        GetThemeStateUseCase(manageSettings = get())
    }
    factory {
        SaveThemeStateUseCase(manageSettings = get())
    }
    factory {
        GetCallsScheduleUseCase(assets = get())
    }
    factory {
        GetHintStateUseCase(manageSettings = get())
    }
    factory {
        UpdateHintStateUseCase(manageSettings = get())
    }
}