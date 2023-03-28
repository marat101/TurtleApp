package com.turtleteam.ui.screens.navigation.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.turtleteam.ui.screens.navigation.controller.Routes
import com.turtleteam.ui.screens.screen_home.HomeScreen
import com.turtleteam.ui.screens.screen_schedule.ScheduleScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.TurtleNavHost(
    navController: NavHostController,
    pagerState: PagerState
) {
    NavHost(
        modifier = Modifier.fillMaxSize().weight(1F),
        navController = navController,
        startDestination = Routes.HOME_PAGER_SCREEN.name
    ) {
        composable(Routes.HOME_PAGER_SCREEN.name) {
            HomeScreen(pagerState)
        }
        composable(
            route = Routes.SCHEDULE_SCREEN.name + "/{name}/{isTeacher}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                nullable = false
            }, navArgument("isTeacher") {
                type = NavType.BoolType
                nullable = false
            })
        ) {
            val name = it.arguments?.getString("name")
                ?: throw NullPointerException("the screen require name argument")
            val isTeacher = it.arguments?.getBoolean("isTeacher")
                ?: throw NullPointerException("the screen require isTeacher argument")

            ScheduleScreen(name, isTeacher)
        }
//        composable(Routes.CALLS_SCHEDULE_LIST.route) {
//            ScheduleListScreen()
//        }
//        }
    }
}





