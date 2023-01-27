package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.NamesList
import com.turtleteam.ui.Communication
import com.turtleteam.ui.DispatchersList
import kotlinx.coroutines.launch

class ScheduleSelectViewModel<out T: SelectVMManageUseCases>(
    private val selectVM: T,
    private val groupListCommunication: Communication<NamesList>,
    private val targetGroupCommunication: Communication<String>,
    private val dispatchersList: DispatchersList
) : ViewModel() {
    init {
        updateGroupsList()
        targetGroupCommunication.map(selectVM.getLastTarget())
    }

    fun pinOrUnpinItem(item: String) {
        groupListCommunication.map(
            selectVM.setPinnedList(
                groupListCommunication.observe().value,
                item
            )
        )
    }

    fun updateGroupsList() = viewModelScope.launch(dispatchersList.dispatcherIO()) {
        groupListCommunication.map(
            selectVM.groupsList()
        )
    }

    fun getGroupsListFlow() = groupListCommunication.observe()

    fun getTargetGroupFlow() = targetGroupCommunication.observe()

    fun setTargetGroup(group: String) {
        selectVM.setLastTarget(group)
        targetGroupCommunication.map(group)
    }
}