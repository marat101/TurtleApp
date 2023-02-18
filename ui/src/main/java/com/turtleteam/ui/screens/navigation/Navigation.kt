package com.turtleteam.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.turtleteam.ui.screens.morescreen.MoreScreen
import com.turtleteam.ui.screens.schedulelist.ScheduleListScreen
import com.turtleteam.ui.screens.schedulescreen.ScheduleScreen
import com.turtleteam.ui.screens.schedulescreen.ScheduleScreenViewModel
import com.turtleteam.ui.screens.scheduleselect.ScheduleSelectScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TurtleNavHost(
    navController: NavHostController,
    pagerState: PagerState,
) {
    NavHost(navController = navController, startDestination = Routes.HOME_PAGER_SCREEN.route) {
        composable(Routes.HOME_PAGER_SCREEN.route) {
            HorizontalPager(count = 3, state = pagerState) {
                when (it) {
                    0 ->
                        ScheduleSelectScreen(
                            navController,
                            false,
                            koinViewModel(named("groups"))
                        )

                    1 ->
                        ScheduleSelectScreen(
                            navController,
                            true,
                            koinViewModel(named("teachers"))
                        )

                    2 -> MoreScreen(navController)
                }
            }
        }
        composable(
            route = Routes.SCHEDULE_SCREEN.route + "/{name}/{isTeacher}",
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
            val vModel: ScheduleScreenViewModel =
                if (isTeacher) koinViewModel(named("teacher"))
                else koinViewModel(named("group"))

            ScheduleScreen(name, vModel)
        }
        composable(Routes.CALLS_SCHEDULE_LIST.route) {
            ScheduleListScreen()
        }
    }
}





