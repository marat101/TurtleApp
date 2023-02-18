package com.turtleteam.ui.navigation

import androidx.navigation.NavHostController
import com.turtleteam.ui.screens.navigation.Routes

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
        navController?.navigate(Routes.SCHEDULE_SCREEN.route + "/$name/$isTeacher")
    }

    override fun navigateToHomeScreen() {
        navController?.navigate(Routes.HOME_PAGER_SCREEN.route)
    }

    override fun navigateToCallsSchedule() {
        navController?.navigate(Routes.CALLS_SCHEDULE_LIST.route)
    }
}