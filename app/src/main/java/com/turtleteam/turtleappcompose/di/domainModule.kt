package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.*
import com.turtleteam.domain.usecases_impl.groups.*
import com.turtleteam.domain.usecases_impl.teachers.*
import com.turtleteam.domain.usecases_impl.usersettings.*
import com.turtleteam.domain.usecases_impl.widget.GetScheduleWidget
import com.turtleteam.domain.usecases_impl.widget.SaveScheduleWidget
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {

    val groupsKey = "Groups"
    val teachersKey = "Teachers"

    // Группы
    factory {
        GetGroupsListUseCase(repository = get(named("groups")))
    }
    factory<GetScheduleUC>(named(groupsKey)) {
        GetGroupScheduleUseCase(repository = get(named("groups")))
    }
    factory<GetSavedScheduleUC>(named(groupsKey)) {
        GetSavedGroupScheduleUseCase(repository = get(named("groups")))
    }
    factory<SaveScheduleUC>(named(groupsKey)) {
        SaveGroupScheduleUseCase(repository = get(named("groups")))
    }
    factory<GetPinnedListUC>(named(groupsKey)) {
        GetGroupsAndPinnedListUseCase(repository = get(named("groups")))
    }
    factory<SetPinnedListUC>(named(groupsKey)) {
        SetPinnedGroupsListUseCase(repository = get(named("groups")))
    }
    factory<GetNamesListUC>(named(groupsKey)) {
        GetGroupsListUseCase(repository = get(named("groups")))
    }
    factory<GetSavedNamesListUC>(named(groupsKey)) {
        GetSavedGroupsListUseCase(repository = get(named("groups")))
    }
    factory<GetLastTargetUC>(named(groupsKey)) {
        GetLastTargetGroupUseCase(repository = get(named("groups")))
    }
    factory<SetLastTargetUC>(named(groupsKey)) {
        SetLastTargetGroupUseCase(repository = get(named("groups")))
    }

    // Преподаватели
    factory {
        GetTeachersListUseCase(repository = get(named("teachers")))
    }
    factory<GetScheduleUC>(named(teachersKey)) {
        GetTeacherScheduleUseCase(repository = get(named("teachers")))
    }
    factory<GetSavedScheduleUC>((named(teachersKey))) {
        GetSavedTeacherScheduleUseCase(repository = get(named("teachers")))
    }
    factory<SaveScheduleUC>(named(teachersKey)) {
        SaveTeacherScheduleUseCase(repository = get(named("teachers")))
    }
    factory<GetPinnedListUC>(named(teachersKey)) {
        GetTeachersAndPinnedListUseCase(repository = get(named("teachers")))
    }
    factory<SetPinnedListUC>(named(teachersKey)) {
        SetPinnedTeachersListUseCase(repository = get(named("teachers")))
    }
    factory<GetNamesListUC>(named(teachersKey)) {
        GetTeachersListUseCase(repository = get(named("teachers")))
    }
    factory<GetSavedNamesListUC>(named(teachersKey)) {
        GetSavedTeachersListUseCase(repository = get(named("teachers")))
    }
    factory<GetLastTargetUC>(named(teachersKey)) {
        GetLastTargetTeacherUseCase(repository = get(named("teachers")))
    }
    factory<SetLastTargetUC>(named(teachersKey)) {
        SetLastTargetTeacherUseCase(repository = get(named("teachers")))
    }

    // Widget
    factory {
        GetScheduleWidget(get())
    }
    factory {
        SaveScheduleWidget(get())
    }

    // Utils
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