package com.turtleteam.ui.di

import com.turtleteam.ui.ActivityViewModel
import com.turtleteam.ui.screens.schedulelist.ScheduleListViewModel
import com.turtleteam.ui.screens.schedulescreen.ScheduleCommunication
import com.turtleteam.ui.screens.schedulescreen.ScheduleScreenViewModel
import com.turtleteam.ui.screens.schedulescreen.ScheduleVMManageUseCases
import com.turtleteam.ui.screens.scheduleselect.GroupListCommunication
import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectViewModel
import com.turtleteam.ui.screens.scheduleselect.SelectVMManageUseCases
import com.turtleteam.ui.screens.scheduleselect.TargetGroupCommunication
import com.turtleteam.ui.utils.DispatchersList
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val uiModule = module {
    viewModel(named("groups")) {
        ScheduleSelectViewModel(
            selectVM = SelectVMManageUseCases.Groups(
                groupsList = get(),
                getLastTargetGroupUseCase = get(),
                setLastTargetGroupUseCase = get(),
                setPinnedList = get(),
                updateHintStateUseCase = get(),
                getHintStateUseCase = get(),
            ),
            groupListCommunication = GroupListCommunication(),
            dispatchersList = DispatchersList.Base(),
            targetGroupCommunication = TargetGroupCommunication()
        )
    }
    viewModel(named("teachers")) {
        ScheduleSelectViewModel(
            selectVM = SelectVMManageUseCases.Teachers(
                teachersList = get(),
                getLastTargetTeacherUseCase = get(),
                setLastTargetTeacherUseCase = get(),
                setPinnedList = get(),
                updateHintStateUseCase = get(),
                getHintStateUseCase = get(),
            ),
            groupListCommunication = GroupListCommunication(),
            dispatchersList = DispatchersList.Base(),
            targetGroupCommunication = TargetGroupCommunication()
        )
    }

    viewModel(named("group")) {
        ScheduleScreenViewModel(
            ScheduleVMManageUseCases.Group(
                getGroupScheduleUseCase = get(),
                saveGroupScheduleUseCase = get(),
                getSavedGroupScheduleUseCase = get()
            ),
            communication = ScheduleCommunication(),
            dispatchersList = DispatchersList.Base()
        )
    }

    viewModel(named("teacher")) {
        ScheduleScreenViewModel(
            ScheduleVMManageUseCases.Teacher(
                getTeachersScheduleUseCase = get(),
                saveTeacherScheduleUseCase = get(),
                getSavedTeacherScheduleUseCase = get()
            ),
            communication = ScheduleCommunication(),
            dispatchersList = DispatchersList.Base()
        )
    }
    viewModel {
        ScheduleListViewModel(
            getCallsScheduleUseCase = get()
        )
    }
    viewModel {
        ActivityViewModel(
            getThemeStateUseCase = get(),
            saveThemeStateUseCase = get()
        )
    }
}