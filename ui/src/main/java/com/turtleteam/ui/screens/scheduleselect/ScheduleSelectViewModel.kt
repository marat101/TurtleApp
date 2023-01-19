package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.usecases.GetGroupsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleSelectViewModel(private val groupsList: GetGroupsListUseCase) : ViewModel() {

    private val _groups = MutableStateFlow<List<String>>(emptyList())
    val groups = _groups.asStateFlow()

    fun getGroupsList() = viewModelScope.launch(Dispatchers.IO) {
        _groups.value = groupsList.execute()
    }
}