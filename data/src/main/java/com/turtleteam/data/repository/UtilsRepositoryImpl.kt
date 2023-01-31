package com.turtleteam.data.repository

import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.repository.UtilsRepository

class UtilsRepositoryImpl(
    private val preferencesStore: PreferencesStore
):UtilsRepository {
    override fun saveThemeState(isDarkThemeOn: Boolean) {
        preferencesStore.saveTheme(isDarkThemeOn)
    }

    override fun getThemeState(): Boolean {
        return preferencesStore.setSavedTheme()
    }
}