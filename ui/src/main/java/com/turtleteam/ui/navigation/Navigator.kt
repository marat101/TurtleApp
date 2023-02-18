package com.turtleteam.ui.navigation

interface Navigator {

    fun navigateBack()

    fun navigateToHomeScreen(name: String, isTeacher: Boolean)

    fun navigateToScheduleScreen()

    fun navigateToCallsSchedule()
}