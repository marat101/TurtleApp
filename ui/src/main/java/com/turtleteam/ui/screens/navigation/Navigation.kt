package com.turtleteam.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(
            route = Routes.SCHEDULE_SCREEN.route + "/{name}",
            arguments = listOf(
                navArgument("name"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            val name = it.arguments?.getString("name") ?: throw NullPointerException("the screen require name argument")
            ScheduleScreen(navController,name)
        }
    }
}






