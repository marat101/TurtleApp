package com.turtleteam.ui.screens.screen_additional.components

import android.content.res.Configuration
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.callschedule.Calls
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CallsList(
    calls: List<Calls>,
    onBackPress: () -> Unit
) {
    BackHandler(true) {
        onBackPress()
    }
    val screen = LocalConfiguration.current

    val isTablet = ((screen.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK)
            >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    val divider = if (!isTablet) if (screen.orientation == 1) 5.4F else 3F else 3F

    val contentPadding = (screen.screenWidthDp.toFloat() / divider).dp
    val listState = rememberLazyListState(1)


    LazyRow(
        state = listState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = listState),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(horizontal = contentPadding),
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
            fontFamily = fontGanelas,
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
                        fontFamily = fontGanelas,
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
                        fontFamily = fontGanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor
                    )
                    Text(
                        text = "-",
                        fontFamily = fontGanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor
                    )
                    Text(
                        text = it.end,
                        fontFamily = fontGanelas,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = TurtleTheme.color.callTimeColor
                    )
                }
            }
        }
    }
}