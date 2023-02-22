package com.turtleteam.ui.di

import com.turtleteam.ui.screens.common.viewmodel.NamesListUsecasesProvider
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.controller.NavigationControllerImpl
import com.turtleteam.ui.screens.navigation.controller.Navigator
import com.turtleteam.ui.screens.common.viewmodel.NamesViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val uiModule = module {

    val groupsKey = "Groups"
    val teachersKey = "Teachers"

    single<NavigationController> {
        NavigationControllerImpl()
    }

    single<Navigator> {
        get<NavigationController>()
    }

    viewModel<NamesListViewModel>(named(groupsKey)) {
        NamesViewModelImpl(
            get(),
            NamesListUsecasesProvider(
                get(named(groupsKey)),
                get(named(groupsKey)),
                get(named(groupsKey)),
                get(named(groupsKey))
            )
        )
    }

    viewModel<NamesListViewModel>(named(teachersKey)) {
        NamesViewModelImpl(
            get(),
            NamesListUsecasesProvider(
                get(named(teachersKey)),
                get(named(teachersKey)),
                get(named(teachersKey)),
                get(named(teachersKey))
            )
        )
    }

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