package com.turtleteam.ui.screens.navigation.view

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.turtleteam.ui.screens.navigation.controller.Routes
import com.turtleteam.ui.screens.screen_home.HomeScreen
import com.turtleteam.ui.screens.screen_schedule.ScheduleScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.TurtleNavHost(
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .weight(1F),
        navController = navController,
        startDestination = Routes.HOME_PAGER_SCREEN.name
    ) {
        composable(Routes.HOME_PAGER_SCREEN.name) {
            HomeScreen()
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
        composable(
            route = Routes.LINK.name + "/{link}",
            arguments = listOf(navArgument("link") {
                type = NavType.StringType
                nullable = false
            }),
            deepLinks = listOf()
        ) {
            val linkArg = it.arguments?.getString("link")
                ?: throw NullPointerException("the screen require name argument")
            val context = LocalContext.current
            val link = URLDecoder.decode(linkArg, StandardCharsets.UTF_8.toString())

            LaunchedEffect(key1 = null, block = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
                navController.popBackStack()
            })
        }
    }
}





