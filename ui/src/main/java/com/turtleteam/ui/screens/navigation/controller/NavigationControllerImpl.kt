package com.turtleteam.ui.screens.navigation.controller

import androidx.navigation.NavHostController
import com.turtleteam.ui.utils.TopBarTitleState
import com.turtleteam.ui.utils.TopBarTitleStateImpl
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

interface NavigationController : Navigator, TopBarTitleState {
    fun setNavController(navController: NavHostController?)
}

class NavigationControllerImpl() : NavigationController, TopBarTitleStateImpl() {

    private var navHostController: NavHostController? = null

    override fun setNavController(navController: NavHostController?) {
        navHostController = navController
        navHostController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.route == Routes.HOME_PAGER_SCREEN.name) {
                topBarTitle.value = "TurtleApp"
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

    override fun openLink(link: String) {
        val encodedUrl = URLEncoder.encode(link, StandardCharsets.UTF_8.toString())
        navHostController?.navigate(Routes.LINK.name + "/$encodedUrl")
    }
}