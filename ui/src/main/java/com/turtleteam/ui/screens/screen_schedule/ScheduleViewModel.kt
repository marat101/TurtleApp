package com.turtleteam.ui.screens.screen_schedule

import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.other.StatefulModel
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.GetSavedScheduleUC
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.domain.usecases.SaveScheduleUC
import com.turtleteam.ui.screens.common.viewmodel.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ScheduleViewModel : BaseViewModel() {

    abstract val state: StateFlow<StatefulModel<DaysList>>

    abstract fun getSchedule()
}

class ScheduleViewModelImpl(
    private val getScheduleUC: GetScheduleUC,
    private val getSavedScheduleUC: GetSavedScheduleUC,
    private val saveScheduleUC: SaveScheduleUC,
    private val name: String
) : ScheduleViewModel() {

    private val _state = MutableStateFlow<StatefulModel<DaysList>>(StatefulModel())
    override val state: StateFlow<StatefulModel<DaysList>>
        get() = _state.asStateFlow()

    override fun getSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            handleResult(
                execute = {
                    _state.update {
                        it.copy(
                            data = getScheduleUC.execute(name),
                            loadingState = States.Success
                        )
                    }
                },
                onFailure = {
                    _state.update {
                        it.copy(
                            data = getSavedScheduleUC.execute(name),
                            loadingState = States.Success
                        )
                    }
                },
                finally = {
                    _state.value.data?.let {
                        saveScheduleUC.execute(it)
                    }
                    if (_state.value.data == null) _state.value.loadingState = States.Error
                }
            )
        }
    }
}