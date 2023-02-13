package com.turtleteam.turtleappcompose.di

import com.turtleteam.widget.WidgetViewModel
import com.turtleteam.widget.widget.utils.WidgetDataManage
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val widgetModule = module {

    viewModel(named("WidgetActivityGroups")) {
        WidgetViewModel(
            getScheduleUC = get(named("GroupUseCase")),
            saveScheduleUC = get(named("GroupUseCase")),
            getListAndPinnedListUC = get(named("GroupUseCase")),
            getHintStateUseCase = get(),
            setHintStateUseCase = get(),
            setPinnedGroupsListUseCase = get(named("GroupUseCase")),
            setter = WidgetDataManage.SetData.Base(context = get()),
        )
    }
    viewModel(named("WidgetActivityTeachers")) {
        WidgetViewModel(
            getScheduleUC = get(named("TeacherUseCase")),
            saveScheduleUC = get(named("TeacherUseCase")),
            getListAndPinnedListUC = get(named("TeacherUseCase")),
            getHintStateUseCase = get(),
            setHintStateUseCase = get(),
            setPinnedGroupsListUseCase = get(named("TeacherUseCase")),
            setter = WidgetDataManage.SetData.Base(context = get()),
        )
    }
}