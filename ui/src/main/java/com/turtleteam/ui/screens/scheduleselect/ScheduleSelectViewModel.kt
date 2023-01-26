package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.usecases.groups.GetGroupsAndPinnedListUseCase
import com.turtleteam.domain.usecases.groups.GetLastTargetGroupUseCase
import com.turtleteam.domain.usecases.groups.SetLastTargetGroupUseCase
import com.turtleteam.domain.usecases.groups.SetPinnedGroupsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleSelectViewModel(
    private val groupsList: GetGroupsAndPinnedListUseCase,
    private val getLastTargetGroupUseCase: GetLastTargetGroupUseCase,
    private val setLastTargetGroupUseCase: SetLastTargetGroupUseCase,
    private val setPinndeList: SetPinnedGroupsListUseCase
) : ViewModel() {

    private val _groups = MutableStateFlow(NamesList(emptyList(), emptyList()))
    val groups = _groups.asStateFlow()

    fun setPinnedList(item: String){
        _groups.value = setPinndeList.execute(groups.value, item)
    }

    fun getGroupsList() = viewModelScope.launch(Dispatchers.IO) {
        _groups.value = groupsList.execute()
    }


    private val _currentGroup = MutableStateFlow(getLastTargetGroupUseCase.execute())
    val currentGroup = _currentGroup.asStateFlow()

    fun setGroup(group:String) {
        setLastTargetGroupUseCase.execute(group)
        _currentGroup.value = group
    }
}