package com.turtleteam.ui.screens.navigation.controller

import androidx.navigation.NavHostController

interface NavigationController : Navigator {
    var navController: NavHostController?
}

class NavigationControllerImpl(
    override var navController: NavHostController? = null
) : NavigationController {
    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToScheduleScreen(name: String, isTeacher: Boolean) {
        navController?.navigate(Routes.SCHEDULE_SCREEN.name + "/$name/$isTeacher")
    }

    override fun navigateToHomeScreen() {
        navController?.navigate(Routes.HOME_PAGER_SCREEN.name)
    }

    override fun navigateToCallsSchedule() {
        navController?.navigate(Routes.CALLS_SCHEDULE_LIST.name)
    }
}