package com.turtleteam.ui.screens.screen_home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.turtleteam.ui.screens.navigation.view.BottomNavigationMenu
import com.turtleteam.ui.screens.screen_additional.AdditionalScreen
import com.turtleteam.ui.screens.screen_groups.GroupsScreen
import com.turtleteam.ui.screens.screen_teachers.TeachersScreen
import com.turtleteam.ui.utils.PagerUserScroll
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = getViewModel(),
    userScroll: PagerUserScroll = get()
) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { 3 }
    viewModel.setPager(pagerState)
    val state = viewModel.state.collectAsState()

    Column {
        HorizontalPager(
            userScrollEnabled = userScroll.isUserScrollEnabled.value,
            modifier = modifier.weight(1F),
            beyondBoundsPageCount = 2,
            state = pagerState
        ) { index ->
            when (index) {
                0 -> GroupsScreen(index)
                1 -> TeachersScreen(index)
                2 -> AdditionalScreen(index)
            }
        }
            BottomNavigationMenu(viewModel, pagerState.currentPage)
    }
    LaunchedEffect(key1 = pagerState.targetPage, block = {
        viewModel.onPageChanged(pagerState.targetPage)
    })
    LaunchedEffect(key1 = state.value.currentPage, block = {
        pagerState.animateScrollToPage(state.value.currentPage)
    })
}