package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.groups.*
import com.turtleteam.domain.usecases.teachers.*
import com.turtleteam.domain.usecases.usersettings.*
import com.turtleteam.domain.usecases.widget.GetScheduleWidget
import com.turtleteam.domain.usecases.widget.SaveScheduleWidget
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {

    // Группы
    factory {
        GetGroupsListUseCase(repository = get(named("groups")))
    }
    factory {
        GetGroupScheduleUseCase(repository = get(named("groups")))
    }
    factory {
        GetSavedGroupScheduleUseCase(repository = get(named("groups")))
    }
    factory {
        SaveGroupScheduleUseCase(repository = get(named("groups")))
    }
    factory {
        GetGroupsAndPinnedListUseCase(repository = get(named("groups")))
    }
    factory {
        SetPinnedGroupsListUseCase(repository = get(named("groups")))
    }
    factory {
        GetLastTargetGroupUseCase(repository = get(named("groups")))
    }
    factory {
        SetLastTargetGroupUseCase(repository = get(named("groups")))
    }

    // Преподаватели
    factory {
        GetTeachersListUseCase(repository = get(named("teachers")))
    }
    factory {
        GetTeacherScheduleUseCase(repository = get(named("teachers")))
    }
    factory {
        GetSavedTeacherScheduleUseCase(repository = get(named("teachers")))
    }
    factory {
        SaveTeacherScheduleUseCase(repository = get(named("teachers")))
    }
    factory {
        GetTeachersAndPinnedListUseCase(repository = get(named("teachers")))
    }
    factory {
        SetPinnedTeachersListUseCase(repository = get(named("teachers")))
    }
    factory {
        GetLastTargetTeacherUseCase(repository = get(named("teachers")))
    }
    factory {
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