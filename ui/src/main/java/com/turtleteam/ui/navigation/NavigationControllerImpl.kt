package com.turtleteam.ui.navigation

import androidx.navigation.NavHostController
import com.turtleteam.ui.screens.navigation.Routes

interface NavigationController {
    var navController: NavHostController?
}

class NavigationControllerImpl(
    override var navController: NavHostController? = null
) : NavigationController, Navigator {
    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToHomeScreen(name: String, isTeacher: Boolean) {
        navController?.navigate(Routes.SCHEDULE_SCREEN.route + "/$name/$isTeacher")
    }

    override fun navigateToScheduleScreen() {
    }

    override fun navigateToCallsSchedule() {
    }
}