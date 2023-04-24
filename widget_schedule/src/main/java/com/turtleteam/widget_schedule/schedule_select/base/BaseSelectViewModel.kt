package com.turtleteam.widget_schedule.schedule_select.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.other.StatefulModel
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.usecases.GetNamesListUC
import com.turtleteam.domain.usecases.GetPinnedListUC
import com.turtleteam.widget_schedule.schedule_select.SelectType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseSelectViewModel : ViewModel() {
    abstract val state: StateFlow<StatefulModel<NamesList>>

    abstract fun getList()

    abstract fun clickOnName(name: String)
}

class SelectViewModelImpl(
    private val getPinnedListUC: GetPinnedListUC,
    private val getNamesListUC: GetNamesListUC,
    private val type: SelectType
) : BaseSelectViewModel() {
    private val _state = MutableStateFlow<StatefulModel<NamesList>>(StatefulModel())
    override val state: StateFlow<StatefulModel<NamesList>>
        get() = _state.asStateFlow()

    init {
        getList()
    }
    override fun getList() {
        viewModelScope.launch {
            try {
                val pinned = getPinnedListUC.execute()
                val list = getNamesListUC.execute().toMutableList().apply { removeAll(pinned) }
                _state.update { StatefulModel(NamesList(pinned,list), States.Success) }
                println(_state.value)
            } catch (e: Exception) {
                _state.update { it.copy(loadingState = States.Error) }
            }
        }
    }

    override fun clickOnName(name: String) {

    }
}