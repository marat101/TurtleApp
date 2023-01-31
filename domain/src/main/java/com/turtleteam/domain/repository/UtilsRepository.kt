package com.turtleteam.domain.repository

interface UtilsRepository {
    fun saveThemeState(isDarkThemeOn:Boolean)
    fun getThemeState():Boolean
}