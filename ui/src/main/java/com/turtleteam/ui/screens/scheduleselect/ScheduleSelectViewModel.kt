package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.groups.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleSelectViewModel(
    private val groupsList: GetGroupsAndPinnedListUseCase,
    private val saveSchedule: SaveGroupScheduleUseCase,
    private val savedSchedule: GetSavedGroupScheduleUseCase,
    private val getSchedule: GetGroupScheduleUseCase,
    private val setPinndeList: SetPinnedGroupsListUseCase
) : ViewModel() {

    private val _groups = MutableStateFlow(NamesList(emptyList(), emptyList()))
    val groups = _groups.asStateFlow()

    private val _schedule = MutableStateFlow<States<DaysList>>(States.Loading)
    val schedule = _schedule.asStateFlow()

    fun setPinnedList(item: String) {
        _groups.value = setPinndeList.execute(groups.value, item)
    }

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

    private val _currentGroup = MutableStateFlow("Группы")//todo save and load last used group
    val currentGroup = _currentGroup.asStateFlow()

    fun setGroup(group: String) {
        _currentGroup.value = group
    }

    private suspend fun handleStates(
        list: States<DaysList>,
        onSuccess: suspend (mSchedule: DaysList) -> Unit
    ) {
        when (list) {
            States.ConnectionError,
            is States.Error,
            States.Loading -> {
            }
            States.NotFoundError -> {}
            is States.Success -> {
                onSuccess(list.value)
            }
        }
    }
}