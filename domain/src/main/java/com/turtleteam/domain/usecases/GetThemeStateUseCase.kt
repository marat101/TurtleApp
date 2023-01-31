package com.turtleteam.domain.usecases

import com.turtleteam.domain.repository.UtilsRepository

class GetThemeStateUseCase(private val repository: UtilsRepository) {
    fun execute() = repository.getThemeState()
}