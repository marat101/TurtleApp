package com.turtleteam.ui.di

import com.turtleteam.ui.screens.common.viewmodel.NamesListUsecasesProvider
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.screens.common.viewmodel.NamesViewModelImpl
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.screens.navigation.controller.NavigationControllerImpl
import com.turtleteam.ui.screens.navigation.controller.Navigator
import com.turtleteam.ui.screens.screen_schedule.ScheduleViewModel
import com.turtleteam.ui.screens.screen_schedule.ScheduleViewModelImpl
import com.turtleteam.ui.utils.BackPress
import com.turtleteam.ui.utils.BackPressImpl
import com.turtleteam.ui.utils.PagerListener
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

    single<PagerListener> {
        get<NavigationController>()
    }

    single<BackPress>{
        BackPressImpl()
    }

    viewModel<NamesListViewModel>(named(groupsKey)) { page ->
        NamesViewModelImpl(
            get(),
            NamesListUsecasesProvider(
                get(named(groupsKey)),
                get(named(groupsKey)),
                get(named(groupsKey)),
                get(named(groupsKey)),
                get(),
                get()
            ),
            get(),
            page.get()
        )
    }

    viewModel<NamesListViewModel>(named(teachersKey)) { page ->
        NamesViewModelImpl(
            get(),
            NamesListUsecasesProvider(
                get(named(teachersKey)),
                get(named(teachersKey)),
                get(named(teachersKey)),
                get(named(teachersKey)),
                get(),
                get()
            ),
            get(),
            page.get()
        )
    }

    viewModel<ScheduleViewModel>(named(groupsKey)) { name ->
        ScheduleViewModelImpl(
            get(named(groupsKey)),
            get(named(groupsKey)),
            get(named(groupsKey)),
            name.get()
        )
    }

    viewModel<ScheduleViewModel>(named(teachersKey)) { name ->
        ScheduleViewModelImpl(
            get(named(teachersKey)),
            get(named(teachersKey)),
            get(named(teachersKey)),
            name.get()
        )
    }
}