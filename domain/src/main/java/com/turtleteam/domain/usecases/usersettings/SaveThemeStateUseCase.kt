package com.turtleteam.domain.usecases.usersettings

import com.turtleteam.domain.repository.UtilsRepository

class SaveThemeStateUseCase(private val repository:UtilsRepository) {
    fun execute(isDarkModeOn:Boolean) = repository.saveThemeState(isDarkModeOn)
}