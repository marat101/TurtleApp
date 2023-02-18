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
        GetGroupsListUseCase(repository = get(named("ScheduleGroupsRepository")))
    }
    factory(named("GroupUseCase")) {
        GetGroupScheduleUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind GetScheduleUC::class

    factory(named("GroupUseCase")) {
        GetSavedGroupScheduleUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind GetSavedScheduleUC::class

    factory(named("GroupUseCase")) {
        SaveGroupScheduleUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind SaveScheduleUC::class

    factory(named("GroupUseCase")) {
        GetGroupsAndPinnedListUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind GetListAndPinnedListUC::class

    factory(named("GroupUseCase")) {
        SetPinnedGroupsListUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind SetPinnedListUC::class

    factory(named("GroupUseCase")) {
        GetLastTargetGroupUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind GetLastTargetUC::class
    factory(named("GroupUseCase")) {
        SetLastTargetGroupUseCase(repository = get(named("ScheduleGroupsRepository")))
    } bind SetLastTargetUC::class

    // Преподаватели
    factory(named("TeacherUseCase")) {
        GetTeachersListUseCase(repository = get(named("ScheduleTeachersRepository")))
    }
    factory(named("TeacherUseCase")) {
        GetTeacherScheduleUseCase(repository = get(named("ScheduleTeachersRepository")))
    } bind GetScheduleUC::class
    factory(named("TeacherUseCase")) {
        GetSavedTeacherScheduleUseCase(repository = get(named("ScheduleTeachersRepository")))
    } bind GetSavedScheduleUC::class
    factory(named("TeacherUseCase")) {
        SaveTeacherScheduleUseCase(repository = get(named("ScheduleTeachersRepository")))
    } bind SaveScheduleUC::class
    factory(named("TeacherUseCase")) {
        GetTeachersAndPinnedListUseCase(repository = get(named("ScheduleTeachersRepository")))
    } bind GetListAndPinnedListUC::class
    factory(named("TeacherUseCase")) {
        SetPinnedTeachersListUseCase(repository = get(named("ScheduleTeachersRepository")))
    } bind SetPinnedListUC::class
    factory(named("TeacherUseCase")) {
        GetLastTargetTeacherUseCase(repository = get(named("ScheduleTeachersRepository")))
    } bind GetLastTargetUC::class
    factory(named("TeacherUseCase")) {
        SetLastTargetTeacherUseCase(repository = get(named("ScheduleTeachersRepository")))
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