package com.turtleteam.ui.screens.screen_home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.turtleteam.ui.screens.screen_additional.AdditionalScreen
import com.turtleteam.ui.screens.screen_groups.GroupsScreen
import com.turtleteam.ui.screens.screen_teachers.TeachersScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(pagerState: PagerState, modifier: Modifier = Modifier) {

    val screens = listOf<@Composable () -> Unit>(
            { GroupsScreen() },
            { TeachersScreen() },
            { AdditionalScreen() }
        )

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        count = screens.size,
        state = pagerState
    ) { index ->
        screens[index].invoke()
    }
}