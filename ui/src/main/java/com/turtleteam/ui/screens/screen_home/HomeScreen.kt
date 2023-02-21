package com.turtleteam.ui.screens.screen_home

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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

    HorizontalPager(
        modifier = modifier
            .fillMaxSize()
            .fillMaxHeight(),
        count = 3,
        state = pagerState
    ) { index ->
        when (index) {
            0 -> GroupsScreen()
            1 -> TeachersScreen()
            2 -> AdditionalScreen()
        }
    }
}