package com.turtleteam.ui.screens.schedulescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.Communication
import com.turtleteam.ui.DispatchersList
import kotlinx.coroutines.launch

class ScheduleScreenViewModel(
    private val manageUseCases: ScheduleVMManageUseCases,
    private val communication: Communication<States<DaysList>>,
    private val dispatchersList: DispatchersList,
) : ViewModel() {
    fun getFlow() = communication.observe()
    fun updateSchedule(name: String) =
        viewModelScope.launch(dispatchersList.dispatcherIO()) {
            communication.map(States.Loading)

            val localSchedule: States<DaysList> = manageUseCases.getSavedSchedule(name)
            val isHasLocalData = localSchedule is States.Success
            if (isHasLocalData) communication.map(localSchedule)

            val schedule: States<DaysList> = manageUseCases.getSchedule(name)
            if (schedule is States.Success) {
                communication.map(schedule)
                manageUseCases.saveSchedule(schedule.value)
            } else if (!isHasLocalData) communication.map(States.Error())
        }
}