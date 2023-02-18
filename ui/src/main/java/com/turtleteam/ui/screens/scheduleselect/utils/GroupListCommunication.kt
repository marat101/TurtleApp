package com.turtleteam.ui.screens.scheduleselect.utils

import com.android.turtleapp.data.model.teachersandgroups.NamesList
import com.turtleteam.ui.utils.Communication
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