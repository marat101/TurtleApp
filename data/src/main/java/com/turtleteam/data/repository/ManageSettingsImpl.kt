package com.turtleteam.data.repository

import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.repository.ManageSettings

class ManageSettingsImpl(
    private val preferencesStore: PreferencesStore
):ManageSettings {
    override fun saveThemeState(isDarkThemeOn: Boolean) {
        preferencesStore.saveTheme(isDarkThemeOn)
    }

    override fun getThemeState(): Boolean {
        return preferencesStore.setSavedTheme()
    }

    override fun updateHintState(state: Boolean) {
        preferencesStore.updateHintState(state)
    }

    override fun getHintState(): Boolean {
        return preferencesStore.isShowHint()
    }
}