package com.turtleteam.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    current: Int
) {
    val pagerState = rememberLazyListState()

    val widthLocal = LocalDensity.current

    LazyRow(
        Modifier
            .padding(vertical = 20.dp)
            .width(150.dp)
            .then(modifier),
        state = pagerState,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { iteration ->
            val color = if (current == iteration) {
                Color.White
            } else Color.Gray

            item {
                Box(
                    modifier = Modifier
                        .background(color, CircleShape)
                        .size(8.dp)
                )
            }
        }
    }
    LaunchedEffect(key1 = current, block = {
        Log.e("coooooooo", current.toString())
//        pagerState.animateScrollToItem(current, widthLocal.density)
    })
}