package com.turtleteam.turtleappcompose.di

import com.turtleteam.ui.screens.schedulescreen.ScheduleScreenViewModel
import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {

        /**
         * вью модель для теста, можно удалить\изменить
         */

        ScheduleSelectViewModel(
            groupsList = get(),
            getLastTargetGroupUseCase = get(),
            setLastTargetGroupUseCase = get(),
            setPinndeList = get()
        )

    }
    viewModel {
        ScheduleScreenViewModel(
            getGroupScheduleUseCase = get(),
            saveGroupScheduleUseCase = get(),
            getSavedGroupScheduleUseCase = get()

        )
    }
}