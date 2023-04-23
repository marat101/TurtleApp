package com.turtleteam.widget_schedule.di

import com.turtleteam.domain.repository.WidgetRepository
import com.turtleteam.widget_schedule.ScheduleProvider
import com.turtleteam.widget_schedule.ScheduleProviderImpl
import com.turtleteam.widget_schedule.widgetprovider.WidgetUpdate
import com.turtleteam.widget_schedule.widgetprovider.WidgetUpdateImpl
import com.turtleteam.widget_schedule.repository.WidgetRepositoryImpl
import org.koin.dsl.module

val widgetModule = module {

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