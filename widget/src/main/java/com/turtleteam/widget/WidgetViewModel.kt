package com.turtleteam.widget

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.turtleapp.data.model.teachersandgroups.NamesList
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.usersettings.GetHintStateUseCase
import com.turtleteam.domain.usecases.usersettings.UpdateHintStateUseCase
import com.turtleteam.domain.utils.GetListAndPinnedListUC
import com.turtleteam.domain.utils.GetScheduleUC
import com.turtleteam.domain.utils.SaveScheduleUC
import com.turtleteam.domain.utils.SetPinnedListUC
import com.turtleteam.widget.widget.utils.WidgetDataManage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class WidgetViewModel(
    private val getScheduleUC: GetScheduleUC,
    private val saveScheduleUC: SaveScheduleUC,
    private val getListAndPinnedListUC: GetListAndPinnedListUC,
    private val getHintStateUseCase: GetHintStateUseCase,
    private val setHintStateUseCase: UpdateHintStateUseCase,
    private val setPinnedGroupsListUseCase: SetPinnedListUC,
    private val setter: WidgetDataManage.SetData
) : ViewModel() {
    val namesFlow = MutableStateFlow(NamesList(emptyList(), emptyList()))

    suspend fun setNewSchedule(name: String) {
        val sch = getScheduleUC.execute(name)
        if (sch is States.Success){
            saveScheduleUC.execute(sch.value)
            setter.setSchedule(DaysList.toJson(sch.value))
            setter.setCurrentGroupName(name)
            setter.setFirstDay()
        }
    }

    fun updateList() = viewModelScope.launch {
        namesFlow.emit(getListAndPinnedListUC.execute())
    }

    fun notShowHint() = setHintStateUseCase.execute(false)
    fun getHintState() = getHintStateUseCase.execute()
    fun pinOrUnpinItem(title: String) {
        namesFlow.value = setPinnedGroupsListUseCase.execute(namesFlow.value, title)
    }
}