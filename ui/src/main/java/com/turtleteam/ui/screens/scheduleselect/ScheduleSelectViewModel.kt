package com.turtleteam.ui.screens.scheduleselect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.turtleapp.data.model.teachersandgroups.NamesList
import com.turtleteam.ui.navigation.Navigator
import com.turtleteam.ui.screens.scheduleselect.utils.SelectVMManageUseCases
import com.turtleteam.ui.utils.Communication
import com.turtleteam.ui.utils.DispatchersList
import kotlinx.coroutines.launch

class ScheduleSelectViewModel(
    private val selectVM: SelectVMManageUseCases,
    private val groupListCommunication: Communication<NamesList>,
    private val targetGroupCommunication: Communication<String>,
    private val dispatchersList: DispatchersList,
    private val navigator: Navigator
) : ViewModel() {
    init {
        targetGroupCommunication.map(selectVM.getLastTarget())
    }

    fun pinOrUnpinItem(item: String) {
        groupListCommunication.map(
            selectVM.setPinnedList(groupListCommunication.observe().value, item)
        )
    }

    fun getGroupsListFlow() = groupListCommunication.observe()
    fun updateGroupsList() = viewModelScope.launch(dispatchersList.dispatcherIO()) {
        groupListCommunication.map(
            selectVM.groupsList()
        )
    }

    fun getTargetGroupFlow() = targetGroupCommunication.observe()
    fun setTargetGroup(group: String) {
        selectVM.setLastTarget(group)
        targetGroupCommunication.map(group)
    }

    fun notShowHint() = selectVM.updateTipState(false)
    fun getHintState() = selectVM.getTipState()

    fun navigateToScheduleScreen(name: String, isTeacher: Boolean){
        navigator.navigateToScheduleScreen(name, isTeacher)
    }
}