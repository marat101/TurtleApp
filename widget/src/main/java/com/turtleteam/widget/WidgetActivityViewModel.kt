package com.turtleteam.widget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.GetGroupsAndPinnedListUseCase
import com.turtleteam.domain.usecases.teachers.GetTeachersAndPinnedListUseCase
import com.turtleteam.widget.widget.utils.WidgetDataManage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


class WidgetActivityViewModel(
    private val getGroups: GetGroupsAndPinnedListUseCase,
    private val getTeachers: GetTeachersAndPinnedListUseCase,
    private val getSch: GetGroupScheduleUseCase,
    private val setter: WidgetDataManage.SetData
) : ViewModel() {
    val namesFlow = MutableSharedFlow<List<String>>()

    init {
        viewModelScope.launch {
            val groups = getGroups.execute()
            val teachers = getTeachers.execute()
            val all = groups.pinned + teachers.pinned + groups.groups  + teachers.groups
            namesFlow.emit(all)
        }
    }

    suspend fun setNewSchedule(name: String) {
            val sch = getSch.execute(name)
            if (sch is States.Success){
                setter.setSchedule(DaysList.toJson(sch.value))
                setter.setCurrentGroupName(name)
                setter.setFirstDay()

            }
    }
}