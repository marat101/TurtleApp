package com.turtleteam.ui.screens.screen_additional.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.callschedule.Calls
import ru.turtleteam.theme.TurtleTheme
import ru.turtleteam.theme.fontQanelas

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CallsList(
    calls: List<Calls>,
    onBackPress: () -> Unit
) {
    BackHandler(true) {
        onBackPress()
    }

    val listState = rememberLazyListState(1)
    val flingBehavior = rememberSnapFlingBehavior(listState)
    val alpha = remember { Animatable(0F) }
    val paddings = with(LocalDensity.current){ 8.dp.toPx() }

    LazyRow(
        modifier = Modifier.alpha(alpha.value),
        state = listState,
        flingBehavior = flingBehavior,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(calls) {
            CallsItem(calls = it)
        }
    }

    LaunchedEffect(key1 = Unit, block = {
        val itemInfo = listState.layoutInfo.visibleItemsInfo.firstOrNull { it.index == 1 }
        if (itemInfo != null) {
            val center = listState.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            listState.scrollBy((childCenter - center + paddings).toFloat())
        } else {
            listState.scrollToItem(1)
        }
        alpha.animateTo(1f, tween(durationMillis = 350, easing = FastOutSlowInEasing))
    })
}

@Composable
fun CallsItem(calls: Calls) {
    Column(
        modifier = Modifier
            .background(
                TurtleTheme.color.transparentBackground,
                RoundedCornerShape(12.dp)
            )
            .size(238.dp, 306.dp)
            .padding(vertical = 5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            modifier = Modifier
                .padding(vertical = 7.dp)
                .align(Alignment.CenterHorizontally),
            text = calls.type,
            fontFamily = fontQanelas,
            fontSize = 18.sp,
            color = TurtleTheme.color.callTypeColor,
            fontWeight = FontWeight(700)
        )
        calls.calls.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .background(TurtleTheme.color.numberBackground, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = it.number.toString(),
                        fontFamily = fontQanelas,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight(700)
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(start = 23.dp)
                        .width(127.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = it.start,
                        fontFamily = fontQanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor
                    )
                    Text(
                        text = "-",
                        fontFamily = fontQanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor
                    )
                    Text(
                        text = it.end,
                        fontFamily = fontQanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor
                    )
                }
            }
        }
    }
}