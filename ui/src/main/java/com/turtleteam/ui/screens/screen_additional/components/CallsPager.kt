package com.turtleteam.ui.screens.screen_additional.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.callschedule.Calls
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontQanelas

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CallsList(
    calls: List<Calls>,
    onBackPress: () -> Unit
) {
    BackHandler(true) {
        onBackPress()
    }

    val offset = with(LocalDensity.current) { 372.dp.toPx() }

    val listState = rememberLazyListState(initialFirstVisibleItemScrollOffset = offset.toInt()/2)
    val flingBehavior = rememberSnapFlingBehavior(listState)

    LazyRow(
        state = listState,
        flingBehavior = flingBehavior,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(calls) {
            CallsItem(calls = it)
        }
    }
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