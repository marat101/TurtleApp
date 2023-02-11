package com.turtleteam.turtleappcompose.di

import com.turtleteam.widget.WidgetActivityViewModel
import com.turtleteam.widget.widget.utils.WidgetDataManage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val widgetModule = module {
    viewModel {
        WidgetActivityViewModel(
            getGroups = get(),
            getTeachers = get(),
            getSch = get(),
            setter = WidgetDataManage.SetData.Base(context = get())
        )
    }
}