package com.turtleteam.turtleappcompose.di

import com.turtleteam.domain.usecases.groups.*
import com.turtleteam.domain.usecases.teachers.*
import com.turtleteam.domain.usecases.usersettings.GetCallsScheduleUseCase
import com.turtleteam.domain.usecases.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases.usersettings.SaveThemeStateUseCase
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
        SetPinnedGroupsListUseCase(repository = get())
    }
    factory {
        GetLastTargetGroupUseCase(repository = get())
    }
    factory {
        SetLastTargetGroupUseCase(repository = get())
    }

    // Преподаватели
    factory {
        GetTeachersListUseCase(repository = get())
    }
    factory {
        GetTeacherScheduleUseCase(repository = get())
    }
    factory {
        GetSavedTeacherScheduleUseCase(repository = get())
    }
    factory {
        SaveTeacherScheduleUseCase(repository = get())
    }
    factory {
        GetTeachersAndPinnedListUseCase(repository = get())
    }
    factory {
        SetPinnedTeachersListUseCase(repository = get())
    }
    factory {
        GetLastTargetTeacherUseCase(repository = get())
    }
    factory {
        SetLastTargetTeacherUseCase(repository = get())
    }
    //Utils
    factory {
        GetThemeStateUseCase(repository = get())
    }
    factory {
        SaveThemeStateUseCase(repository = get())
    }
    factory {
        GetCallsScheduleUseCase(assets = get())
    }
}