package com.turtleteam.ui.screens.screen_schedule.layouts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.screens.screen_schedule.components.DateItem
import com.turtleteam.ui.screens.screen_schedule.components.PairItem
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ScheduleLayout(data: DaysList) {

    val visible = remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()

    AnimatedVisibility(
        visible = visible.value,
        enter = scaleIn(
            initialScale = 0.6F,
            animationSpec = tween(
                delayMillis = 150,
                durationMillis = 150,
                easing = LinearEasing
            )
        ) + fadeIn(
            initialAlpha = 0F,
            animationSpec = tween(
                delayMillis = 150,
                durationMillis = 450,
                easing = LinearEasing
            )
        )
    ) {
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 1,
            pageCount = data.days.size,
            modifier = Modifier.fillMaxSize()) { page ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(
                    top = 30.dp,
                    end = 16.dp,
                    start = 16.dp,
                    bottom = 16.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                stickyHeader {
                    DateItem(day = data.days[page])
                }
                items(data.days[page].apairs) {
                    PairItem(pairs = it, pagerState.isScrollInProgress)
                }
            }
        }
    }
    LaunchedEffect(key1 = visible, block = {
        delay(150)
        visible.value = true
    })
}
