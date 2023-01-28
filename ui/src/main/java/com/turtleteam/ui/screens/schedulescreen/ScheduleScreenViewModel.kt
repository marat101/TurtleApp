package com.turtleteam.ui.screens.schedulescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.Communication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleScreenViewModel<out T:ScheduleVMManageUseCases>(
    private val manageUseCases:T,
    private val communication: Communication<States<DaysList>>,
//    private val getGroupScheduleUseCase: GetGroupScheduleUseCase,
//    private val saveGroupScheduleUseCase: SaveGroupScheduleUseCase,
//    private val getSavedGroupScheduleUseCase: GetSavedGroupScheduleUseCase
) : ViewModel() {
//    private val _scheduleFlow = MutableStateFlow<States<DaysList>>(States.Loading)
//    val scheduleFlow = _scheduleFlow.asStateFlow()
    fun getFlow() = communication.observe()
    fun updateSchedule(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            communication.map(manageUseCases.getSavedSchedule(name))

            val schedule = manageUseCases.getSchedule(name)

            if (schedule is States.Success) {
                communication.map(schedule)
                manageUseCases.saveSchedule(schedule.value)
            }
        }
    }
}