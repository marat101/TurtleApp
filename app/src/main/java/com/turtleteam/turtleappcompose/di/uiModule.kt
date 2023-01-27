package com.turtleteam.turtleappcompose.di

import com.turtleteam.ui.DispatchersList
import com.turtleteam.ui.screens.schedulescreen.ScheduleScreenViewModel
import com.turtleteam.ui.screens.scheduleselect.GroupListCommunication
import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectViewModel
import com.turtleteam.ui.screens.scheduleselect.TargetGroupCommunication
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
            setPinnedList = get(),
            groupListCommunication =GroupListCommunication(),
            dispatchersList = DispatchersList.Base(),
            targetGroupCommunication = TargetGroupCommunication()
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