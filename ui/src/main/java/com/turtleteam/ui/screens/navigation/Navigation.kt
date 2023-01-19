package com.turtleteam.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectScreen

@Composable
fun TurtleNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController , startDestination = Routes.HOMESCREEN.route){
        composable(Routes.HOMESCREEN.route){ ScheduleSelectScreen(navController) }
    }
}