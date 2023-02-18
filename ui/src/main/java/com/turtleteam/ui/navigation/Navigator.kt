package com.turtleteam.ui.navigation

interface Navigator {

    fun navigateBack()

    fun navigateToScheduleScreen(name: String, isTeacher: Boolean)

    fun navigateToHomeScreen()

    fun navigateToCallsSchedule()
}