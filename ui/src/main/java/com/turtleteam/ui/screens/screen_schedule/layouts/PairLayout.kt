package com.turtleteam.ui.screens.screen_schedule.layouts

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.ui.screens.screen_schedule.components.DayItem
import com.turtleteam.ui.screens.screen_schedule.components.PairItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PairLayout(day: Day) {
    val pagerState = rememberPagerState()
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        items(items = day.apairs) { pair ->
            Column(Modifier.padding(bottom = 14.dp)) {
                if (pair.apair.size > 1){
                    HorizontalPager(pageCount = pair.apair.size) { page ->
                        PairItem(pairs = pair, slider = true, pagerState = pagerState)
                    }
                    Log.e("aasd", "Инглиш")
                } else {
                    PairItem(pairs = pair)
                }
            }
        }
    }
}
