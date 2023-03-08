@file:OptIn(ExperimentalPagerApi::class)

package com.turtleteam.ui.screens.navigation.controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi

interface NavigationController : Navigator {
    var navController: NavHostController?
    val bottomBarVisible: MutableState<Boolean>
    fun setTopBarTitle(topBarTitle: MutableState<String>)
}

class NavigationControllerImpl(
    override var navController: NavHostController? = null,
    private var topBarTitle: MutableState<String>? = null,
) : NavigationController {

    override val bottomBarVisible = mutableStateOf(true)

    override fun setTopBarTitle(topBarTitle: MutableState<String>) {
        this.topBarTitle = topBarTitle
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Routes.HOME_PAGER_SCREEN.name) {
                this.topBarTitle?.value = "TurtleApp"
                bottomBarVisible.value = false
            } else {
                bottomBarVisible.value = true
            }
        }
    }

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToScheduleScreen(name: String, isTeacher: Boolean) {
        topBarTitle?.value = name
        navController?.navigate(Routes.SCHEDULE_SCREEN.name + "/$name/$isTeacher")
    }

    override fun navigateToHomeScreen() {
        navController?.navigate(Routes.HOME_PAGER_SCREEN.name)
    }

    override fun navigateToCallsSchedule() {
        topBarTitle?.value = "Расписание звонков"
        navController?.navigate(Routes.CALLS_SCHEDULE_LIST.name)
    }
}