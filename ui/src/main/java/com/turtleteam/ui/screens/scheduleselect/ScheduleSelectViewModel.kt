package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.GetGroupsListUseCase
import com.turtleteam.domain.usecases.groups.GetSavedGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.SaveGroupScheduleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleSelectViewModel(
    private val groupsList: GetGroupsListUseCase,
    private val saveSchedule: SaveGroupScheduleUseCase,
    private val savedSchedule: GetSavedGroupScheduleUseCase,
    private val getSchedule: GetGroupScheduleUseCase
) : ViewModel() {

    private val _groups = MutableStateFlow<List<String>>(emptyList())
    val groups = _groups.asStateFlow()

    private val _schedule = MutableStateFlow<States<DaysList>>(States.Loading)
    val schedule = _schedule.asStateFlow()

    fun getGroupsList() = viewModelScope.launch(Dispatchers.IO) {
        _groups.value = groupsList.execute()
    }

    fun getGroupsSchedule(name: String) = viewModelScope.launch(Dispatchers.IO) {
        _schedule.value = getSchedule.execute(name)
    }

    fun saveSchedule(list: States<DaysList>) = viewModelScope.launch(Dispatchers.IO) {
        handleStates(list) { mSchedule -> saveSchedule.execute(mSchedule) }
    }

    fun getSavedSchedule(name: String) = viewModelScope.launch(Dispatchers.IO) {
        _schedule.value = savedSchedule.execute(name)
    }

    private suspend fun handleStates(
        list: States<DaysList>,
        onSuccess: suspend (mSchedule: DaysList) -> Unit
    ) {
        when (list) {
            States.ConnectionError,
            is States.Error,
            States.Loading -> {}
            States.NotFoundError -> {}
            is States.Success -> {
                onSuccess(list.value)
            }
        }
    }
}