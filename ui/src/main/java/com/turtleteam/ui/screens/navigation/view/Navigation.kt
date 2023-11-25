package com.turtleteam.ui.screens.navigation.view

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.turtleteam.ui.screens.navigation.controller.Routes
import com.turtleteam.ui.screens.screen_home.HomeScreen
import com.turtleteam.ui.screens.screen_notifications.NotificationsScreen
import com.turtleteam.ui.screens.screen_schedule.ScheduleScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ColumnScope.TurtleNavHost(
    navController: NavHostController,
) {
    AnimatedNavHost(
        modifier = Modifier
            .fillMaxSize()
            .weight(1F),
        navController = navController,
        startDestination = Routes.HOME_PAGER_SCREEN.name
    ) {
        composable(Routes.HOME_PAGER_SCREEN.name,
            enterTransition = {
                if (initialState.destination.route == Routes.HOME_PAGER_SCREEN.name) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            exitTransition = {
                if (targetState.destination.route == Routes.NOTIFICATIONS.name) {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popEnterTransition = {
                if (initialState.destination.route == Routes.NOTIFICATIONS.name) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popExitTransition = {
                if (targetState.destination.route == Routes.NOTIFICATIONS.name) {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            }) {
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

        composable(route = Routes.NOTIFICATIONS.name,
            deepLinks = listOf(navDeepLink {
                uriPattern = Routes.NOTIFICATIONS.name
            }),
            enterTransition = {
                if (initialState.destination.route == Routes.HOME_PAGER_SCREEN.name) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                } else null
            },
            exitTransition = {
                if (targetState.destination.route == Routes.HOME_PAGER_SCREEN.name) {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popEnterTransition = {
                if (initialState.destination.route == Routes.HOME_PAGER_SCREEN.name) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            },
            popExitTransition = {
                if (targetState.destination.route == Routes.HOME_PAGER_SCREEN.name) {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                } else null
            }
            ) {
            NotificationsScreen()
        }

        composable(
            route = Routes.LINK.name + "/{link}",
            arguments = listOf(navArgument("link") {
                type = NavType.StringType
                nullable = false
            }),
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





