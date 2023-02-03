package com.turtleteam.domain.repository

interface ManageSettings {
    fun saveThemeState(isDarkThemeOn:Boolean)
    fun getThemeState():Boolean

    fun updateHintState(state:Boolean)
    fun getHintState():Boolean
}