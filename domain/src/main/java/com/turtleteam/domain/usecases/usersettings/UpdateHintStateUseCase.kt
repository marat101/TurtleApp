package com.turtleteam.domain.usecases.usersettings

import com.turtleteam.domain.repository.ManageSettings

class UpdateHintStateUseCase(private val manageSettings: ManageSettings) {
    fun execute(state:Boolean) = manageSettings.updateHintState(state)
}