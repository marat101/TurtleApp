package com.turtleteam.ui.di

import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.controller.NavigationControllerImpl
import com.turtleteam.ui.screens.navigation.controller.Navigator
import org.koin.dsl.module

val uiModule = module {

    single<NavigationController> {
        NavigationControllerImpl()
    }

    single<Navigator> {
        get<NavigationController>()
    }

//    viewModel(named("groups")) {
//        ScheduleSelectViewModel(
//            selectVM = SelectVMManageUseCases.Groups(
//                groupsList = get(),
//                getLastTargetGroupUseCase = get(),
//                setLastTargetGroupUseCase = get(),
//                setPinnedList = get(),
//                updateHintStateUseCase = get(),
//                getHintStateUseCase = get(),
//            ),
//            groupListCommunication = GroupListCommunication(),
//            dispatchersList = DispatchersList.Base(),
//            targetGroupCommunication = TargetGroupCommunication(),
//            navigator = get()
//        )
//    }
//    viewModel(named("teachers")) {
//        ScheduleSelectViewModel(
//            selectVM = SelectVMManageUseCases.Teachers(
//                teachersList = get(),
//                getLastTargetTeacherUseCase = get(),
//                setLastTargetTeacherUseCase = get(),
//                setPinnedList = get(),
//                updateHintStateUseCase = get(),
//                getHintStateUseCase = get(),
//            ),
//            groupListCommunication = GroupListCommunication(),
//            dispatchersList = DispatchersList.Base(),
//            targetGroupCommunication = TargetGroupCommunication(),
//            navigator = get()
//        )
//    }
//
//    viewModel(named("group")) {
//        ScheduleScreenViewModel(
//            ScheduleVMManageUseCases.Group(
//                getGroupScheduleUseCase = get(),
//                saveGroupScheduleUseCase = get(),
//                getSavedGroupScheduleUseCase = get()
//            ),
//            communication = ScheduleCommunication(),
//            dispatchersList = DispatchersList.Base()
//        )
//    }
//
//    viewModel(named("teacher")) {
//        ScheduleScreenViewModel(
//            ScheduleVMManageUseCases.Teacher(
//                getTeachersScheduleUseCase = get(),
//                saveTeacherScheduleUseCase = get(),
//                getSavedTeacherScheduleUseCase = get()
//            ),
//            communication = ScheduleCommunication(),
//            dispatchersList = DispatchersList.Base()
//        )
//    }
//    viewModel {
//        ScheduleListViewModel(
//            getCallsScheduleUseCase = get()
//        )
//    }
}