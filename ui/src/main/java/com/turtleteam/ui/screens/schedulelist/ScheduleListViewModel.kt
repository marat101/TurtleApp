package com.turtleteam.ui.screens.schedulelist

import androidx.lifecycle.ViewModel
import com.turtleteam.domain.usecases.GetCallsScheduleUseCase

class ScheduleListViewModel(
    private val getCallsScheduleUseCase: GetCallsScheduleUseCase
):ViewModel() {
    fun getData() = getCallsScheduleUseCase.execute()
}