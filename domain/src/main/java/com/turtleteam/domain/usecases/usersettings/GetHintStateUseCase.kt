package com.turtleteam.domain.usecases.usersettings

import com.turtleteam.domain.repository.ManageSettings

class GetHintStateUseCase(private val manageSettings: ManageSettings) {
    fun execute(): Boolean = manageSettings.getHintState()
}