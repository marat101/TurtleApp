package com.turtleteam.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.turtleteam.ui.screens.morescreen.MoreScreen
import com.turtleteam.ui.screens.schedulescreen.ScheduleScreen
import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectScreen
import com.turtleteam.ui.screens.teachersscreen.TeachersScreen

@Composable
fun TurtleNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN.route) {
        composable(Routes.HOME_SCREEN.route) { ScheduleSelectScreen(navController) }
        composable(Routes.TEACHERS_SCREEN.route) { TeachersScreen(navController) }
        composable(Routes.MORE_SCREEN.route) { MoreScreen(navController) }
        composable(Routes.SCHEDULE_SCREEN.route) {
        //TODO add args
            ScheduleScreen(navController)
        }
    }
}






