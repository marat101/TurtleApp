package com.turtleteam.ui.screens.screen_schedule

import androidx.lifecycle.ViewModel
import com.turtleteam.domain.model.schedule.DaysList

abstract class ScheduleViewModel: ViewModel() {

    abstract fun getSchedule()

    abstract fun saveSchedule(schedule: DaysList)

    abstract fun getSavedSchedule()
}