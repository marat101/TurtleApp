package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.usecases.groups.GetGroupsAndPinnedListUseCase
import com.turtleteam.domain.usecases.groups.GetLastTargetGroupUseCase
import com.turtleteam.domain.usecases.groups.SetLastTargetGroupUseCase
import com.turtleteam.domain.usecases.groups.SetPinnedGroupsListUseCase
import com.turtleteam.ui.Communication
import com.turtleteam.ui.DispatchersList
import kotlinx.coroutines.launch

class ScheduleSelectViewModel(
    private val groupsList: GetGroupsAndPinnedListUseCase,
    private val getLastTargetGroupUseCase: GetLastTargetGroupUseCase,
    private val setLastTargetGroupUseCase: SetLastTargetGroupUseCase,
    private val setPinnedList: SetPinnedGroupsListUseCase,
    private val groupListCommunication: Communication<NamesList>,
    private val targetGroupCommunication: Communication<String>,
    private val dispatchersList: DispatchersList
) : ViewModel() {
    init {
        updateGroupsList()
        targetGroupCommunication.map(getLastTargetGroupUseCase.execute())
    }

    fun pinOrUnpinItem(item: String) {
        groupListCommunication.map(
            setPinnedList.execute(
                groupListCommunication.observe().value,
                item
            )
        )
    }

    fun updateGroupsList() = viewModelScope.launch(dispatchersList.dispatcherIO()) {
        groupListCommunication.map(groupsList.execute())
    }

    fun getGroupsListFlow() = groupListCommunication.observe()

    fun getTargetGroupFlow() = targetGroupCommunication.observe()

    fun setTargetGroup(group: String) {
        setLastTargetGroupUseCase.execute(group)
        targetGroupCommunication.map(group)
    }
}