package com.turtleteam.widget_schedule.schedule_select.base

import androidx.lifecycle.ViewModel
import com.turtleteam.domain.model.other.StatefulModel
import com.turtleteam.domain.model.teachersandgroups.NamesList
import kotlinx.coroutines.flow.StateFlow

abstract class SelectViewModel: ViewModel() {
    abstract val state: StateFlow<StatefulModel<NamesList>>

    abstract fun getList()

    abstract fun clickOnName(name: String)
}