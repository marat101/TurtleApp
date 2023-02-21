package com.turtleteam.domain.usecases_impl.usersettings

import com.turtleteam.domain.repository.ManageSettings

class GetThemeStateUseCase(private val manageSettings: ManageSettings) {
    fun execute() = manageSettings.getThemeState()
}