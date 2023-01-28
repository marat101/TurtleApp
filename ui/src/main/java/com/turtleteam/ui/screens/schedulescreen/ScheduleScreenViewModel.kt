package com.turtleteam.ui.screens.schedulescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.Communication
import com.turtleteam.ui.DispatchersList
import kotlinx.coroutines.launch

class ScheduleScreenViewModel<out T:ScheduleVMManageUseCases>(
    private val manageUseCases:T,
    private val communication: Communication<States<DaysList>>,
    private val dispatchersList: DispatchersList
) : ViewModel() {
    fun getFlow() = communication.observe()
    fun updateSchedule(name: String) =
        viewModelScope.launch {
            communication.map(manageUseCases.getSavedSchedule(name))

            val schedule: States<DaysList> = manageUseCases.getSchedule(name)

            if (schedule is States.Success) {
                communication.map(schedule)
                manageUseCases.saveSchedule(schedule.value)
            }

        }

}