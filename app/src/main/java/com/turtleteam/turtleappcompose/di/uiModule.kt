package com.turtleteam.turtleappcompose.di

import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel {
        ScheduleSelectViewModel(groupsList = get())
    }
}