package com.turtleteam.domain.usecases_impl.usersettings

import com.turtleteam.domain.repository.ManageSettings

class GetHintStateUseCase(private val manageSettings: ManageSettings) {
    fun execute(): Boolean = manageSettings.getHintState()
}