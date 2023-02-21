package com.turtleteam.ui.screens.navigation.controller

interface Navigator {

    fun navigateBack()

    fun navigateToScheduleScreen(name: String, isTeacher: Boolean)

    fun navigateToHomeScreen()

    fun navigateToCallsSchedule()
}