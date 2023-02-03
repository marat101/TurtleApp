package com.turtleteam.domain.usecases.usersettings

import com.turtleteam.domain.repository.ManageSettings

class SaveThemeStateUseCase(private val manageSettings:ManageSettings) {
    fun execute(isDarkModeOn:Boolean) = manageSettings.saveThemeState(isDarkModeOn)
}