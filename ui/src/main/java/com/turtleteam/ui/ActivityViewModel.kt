package com.turtleteam.ui

import androidx.lifecycle.ViewModel
import com.turtleteam.domain.usecases.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases.usersettings.SaveThemeStateUseCase

class ActivityViewModel(
    private val getThemeStateUseCase: GetThemeStateUseCase,
    private val saveThemeStateUseCase: SaveThemeStateUseCase,
):ViewModel() {
    fun getThemeState() = getThemeStateUseCase.execute()
    fun saveThemeState(state:Boolean) = saveThemeStateUseCase.execute(state)
}