@file:OptIn(ExperimentalPagerApi::class)

package com.turtleteam.ui.screens.navigation.controller

import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.turtleteam.ui.utils.MainScreenStates
import com.turtleteam.ui.utils.MainScreenStatesImpl

interface NavigationController : Navigator, MainScreenStates {
    fun setNavController(navController: NavHostController?)
}

class NavigationControllerImpl() : NavigationController, MainScreenStatesImpl() {

    var navHostController: NavHostController? = null

    override fun setNavController(navController: NavHostController?) {
        navHostController = navController
        navHostController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Routes.HOME_PAGER_SCREEN.name) {
                topBarTitle.value = "TurtleApp"
                bottomBarVisible.value = false
            } else {
                bottomBarVisible.value = true
            }
        }
    }

    override fun navigateBack() {
        navHostController?.popBackStack()
    }

    override fun navigateToScheduleScreen(name: String, isTeacher: Boolean) {
        topBarTitle.value = name
        navHostController?.navigate(Routes.SCHEDULE_SCREEN.name + "/$name/$isTeacher")
    }

    override fun navigateToHomeScreen() {
        navHostController?.navigate(Routes.HOME_PAGER_SCREEN.name)
    }

    override fun navigateToCallsSchedule() {
        topBarTitle.value = "Расписание звонков"
        navHostController?.navigate(Routes.CALLS_SCHEDULE_LIST.name)
    }
}