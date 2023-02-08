package com.turtleteam.ui.screens.scheduleselect.utils

import com.turtleteam.ui.utils.Communication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TargetGroupCommunication: Communication<String> {
    private val flow = MutableStateFlow("")
    override fun map(value: String) {
        flow.value = value
    }

    override fun observe(): StateFlow<String> {
        return flow.asStateFlow()
    }
}