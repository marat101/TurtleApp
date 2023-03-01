@file:OptIn(ExperimentalPagerApi::class)

package com.turtleteam.ui.screens.navigation.controller

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

interface NavigationController : Navigator, PagerController {
    var navController: NavHostController?
    fun setTopBarTitle(topBarTitle: MutableState<String>)
}

class NavigationControllerImpl(
    override var navController: NavHostController? = null,
    private var topBarTitle: MutableState<String>? = null,
    private var pager: PagerState? = null
) : NavigationController {

    val userPgerScroll = mutableStateOf(true)

    override fun setTopBarTitle(topBarTitle: MutableState<String>) {
        this.topBarTitle = topBarTitle
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Routes.HOME_PAGER_SCREEN.name) {
                this.topBarTitle?.value = "TurtleApp"
            }
        }
    }

    override fun setPagerState(pagerState: PagerState) {
        pager = pagerState
    }

    override fun setPagerScroll(enabled: Boolean) {
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