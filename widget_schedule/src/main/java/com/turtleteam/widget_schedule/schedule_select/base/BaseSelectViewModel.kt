package com.turtleteam.widget_schedule.schedule_select.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.other.StatefulModel
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.usecases.GetNamesListUC
import com.turtleteam.domain.usecases.GetPinnedListUC
import com.turtleteam.widget_schedule.schedule_select.SelectType
import com.turtleteam.widget_schedule.widgetprovider.WidgetUpdate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseSelectViewModel : ViewModel() {
    abstract val state: StateFlow<StatefulModel<NamesList>>

    abstract fun getList()

    abstract fun clickOnName(
        name: String, widgetId: Int, context: Context,
        onComplete: () -> Unit
    )
}

class SelectViewModelImpl(
    private val getPinnedListUC: GetPinnedListUC,
    private val getNamesListUC: GetNamesListUC,
    private val type: SelectType,
    private val update: WidgetUpdate
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
                _state.update { StatefulModel(NamesList(pinned, list), States.Success) }
                println(_state.value)
            } catch (e: Exception) {
                _state.update { it.copy(loadingState = States.Error) }
            }
        }
    }

    override fun clickOnName(
        name: String,
        widgetId: Int,
        context: Context,
        onComplete: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            update.upsertWidget(name, type, widgetId, context)
            withContext(Dispatchers.Main.immediate){
                onComplete()
            }
        }
    }
}