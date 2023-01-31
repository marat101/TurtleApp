package com.turtleteam.ui.screens.scheduleselect

import com.turtleteam.domain.model.NamesList
import com.turtleteam.ui.Communication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GroupListCommunication : Communication<NamesList> {
    private val flow = MutableStateFlow<NamesList>(NamesList(emptyList(), emptyList()))
    override fun map(value: NamesList) {
        flow.value = value
    }

    override fun observe(): StateFlow<NamesList> {
        return flow.asStateFlow()
    }
}