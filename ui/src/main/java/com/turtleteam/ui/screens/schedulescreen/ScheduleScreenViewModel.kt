package com.turtleteam.ui.screens.schedulescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.GetSavedGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.SaveGroupScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleScreenViewModel(
    private val getGroupScheduleUseCase: GetGroupScheduleUseCase,
    private val saveGroupScheduleUseCase: SaveGroupScheduleUseCase,
    private val getSavedGroupScheduleUseCase: GetSavedGroupScheduleUseCase
) : ViewModel() {
    private val _scheduleFlow = MutableStateFlow<States<DaysList>>(States.Loading)
    val scheduleFlow = _scheduleFlow.asStateFlow()

    fun updateSchedule(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _scheduleFlow.value = getSavedGroupScheduleUseCase.execute(name)

            val schedule = getGroupScheduleUseCase.execute(name)

            if (schedule is States.Success) {
                _scheduleFlow.value = schedule
                saveGroupScheduleUseCase.execute(schedule.value)
            }
        }
    }
}