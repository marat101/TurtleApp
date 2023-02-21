package com.turtleteam.domain.usecases_impl.usersettings

import com.turtleteam.domain.repository.ManageSettings

class SaveThemeStateUseCase(private val manageSettings: ManageSettings) {
    fun execute(isDarkModeOn: Boolean) = manageSettings.saveThemeState(isDarkModeOn)
}