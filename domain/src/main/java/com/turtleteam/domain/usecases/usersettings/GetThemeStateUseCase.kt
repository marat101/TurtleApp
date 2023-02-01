package com.turtleteam.domain.usecases.usersettings

import com.turtleteam.domain.repository.UtilsRepository

class GetThemeStateUseCase(private val repository: UtilsRepository) {
    fun execute() = repository.getThemeState()
}