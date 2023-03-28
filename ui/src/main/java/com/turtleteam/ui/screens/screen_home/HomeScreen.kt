package com.turtleteam.ui.screens.screen_home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.turtleteam.ui.screens.screen_additional.AdditionalScreen
import com.turtleteam.ui.screens.screen_groups.GroupsScreen
import com.turtleteam.ui.screens.screen_teachers.TeachersScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {


    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        pageCount = 3,
        beyondBoundsPageCount = 2,
        state = pagerState
    ) { index ->
        when (index) {
            0 -> GroupsScreen(index)
            1 -> TeachersScreen(index)
            2 -> AdditionalScreen(index)
        }
    }
}