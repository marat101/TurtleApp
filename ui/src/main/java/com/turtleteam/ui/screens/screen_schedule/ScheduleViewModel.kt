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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class ScheduleViewModel : BaseViewModel() {

    abstract val state: StateFlow<StatefulModel<DaysList>>

    abstract fun getSchedule()
}

class ScheduleViewModelImpl(
    private val getScheduleUC: GetScheduleUC,
    getSavedScheduleUC: GetSavedScheduleUC,
    private val saveScheduleUC: SaveScheduleUC,
    private val name: String
) : ScheduleViewModel() {

    private val loadingState = MutableStateFlow<States>(States.Loading)
    override val state: StateFlow<StatefulModel<DaysList>> =
        getSavedScheduleUC.execute(name)
            .combine(loadingState) { days, state -> StatefulModel(days, state) }
            .stateIn(viewModelScope, SharingStarted.Lazily, StatefulModel())

    init {
        getSchedule()
    }

    override fun getSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            handleResult(
                execute = {
                    loadingState.value = States.Loading
                    saveScheduleUC.execute(getScheduleUC.execute(name))
                    loadingState.value = States.Success
                },
                onFailure = {
                    loadingState.value = States.Error
                }
            )
        }
    }
}