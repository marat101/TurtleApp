package com.turtleteam.ui.screens.screen_schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.GetSavedScheduleUC
import com.turtleteam.domain.usecases.GetScheduleUC
import com.turtleteam.domain.usecases.SaveScheduleUC
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class ScheduleViewModel : ViewModel() {

    abstract val state: StateFlow<States<DaysList>>

    abstract fun getSchedule()

    abstract fun getSavedSchedule()

    abstract fun saveSchedule()
}

class ScheduleViewModelImpl(
    private val getScheduleUC: GetScheduleUC,
    private val getSavedScheduleUC: GetSavedScheduleUC,
    private val saveScheduleUC: SaveScheduleUC,
    private val name: String
) : ScheduleViewModel() {

    private val _state = MutableStateFlow<States<DaysList>>(States.Loading)
    override val state: StateFlow<States<DaysList>>
        get() = _state.asStateFlow()

    override fun getSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = States.Loading
            _state.value = getScheduleUC.execute(name)
        }
    }

    override fun getSavedSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = getSavedScheduleUC.execute(name)
        }
    }

    override fun saveSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is States.Success<DaysList>)
                saveScheduleUC.execute((_state.value as States.Success<DaysList>).value)
        }
    }
}