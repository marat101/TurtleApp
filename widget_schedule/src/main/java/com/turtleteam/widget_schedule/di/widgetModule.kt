package com.turtleteam.widget_schedule.di

import com.turtleteam.domain.repository.WidgetRepository
import com.turtleteam.widget_schedule.ScheduleProvider
import com.turtleteam.widget_schedule.ScheduleProviderImpl
import com.turtleteam.widget_schedule.widgetprovider.WidgetUpdate
import com.turtleteam.widget_schedule.widgetprovider.WidgetUpdateImpl
import com.turtleteam.widget_schedule.repository.WidgetRepositoryImpl
import com.turtleteam.widget_schedule.schedule_select.SelectType
import com.turtleteam.widget_schedule.schedule_select.base.BaseSelectViewModel
import com.turtleteam.widget_schedule.schedule_select.base.SelectViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val widgetModule = module {

    val groupsKey = "Groups"
    val teachersKey = "Teachers"

    viewModel<BaseSelectViewModel>(named(SelectType.GROUP.name)){
        SelectViewModelImpl(
            get(named(groupsKey)),
            get(named(groupsKey)),
            SelectType.GROUP,
            get()
        )
    }

    viewModel<BaseSelectViewModel>(named(SelectType.TEACHER.name)){
        SelectViewModelImpl(
            get(named(teachersKey)),
            get(named(teachersKey)),
            SelectType.TEACHER,
            get()
        )
    }

    single<WidgetRepository> {
        WidgetRepositoryImpl(get())
    }
    single<WidgetUpdate> {
        WidgetUpdateImpl(get(), get())
    }
    single<ScheduleProvider> {
        ScheduleProviderImpl
    }
}