package com.turtleteam.ui.screens.screenhome

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.turtleteam.ui.screens.common.components.ScheduleSelectFrame
import com.turtleteam.ui.theme.TurtleTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(pagerState: PagerState) {

    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        count = 2,
        state = pagerState
    ) { index ->
        when (index) {
            0 -> ScheduleSelectFrame(
                imageId = TurtleTheme.images.selectGroup,
                onOpenList = { /*TODO*/ },
                onNextClick = { /*TODO*/ },
                name = "aaaaaa"
            )
            1 -> ScheduleSelectFrame(
                imageId = TurtleTheme.images.selectTeacher,
                onOpenList = { /*TODO*/ },
                onNextClick = { /*TODO*/ },
                name = "aaaaaa"
            )
        }
    }
}