package com.turtleteam.ui.screens.screen_schedule.layouts

import androidx.compose.animation.*
import androidx.compose.animation.R
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.screens.screen_schedule.components.DayItem
import com.turtleteam.ui.theme.fontQanelas
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ScheduleLayout(value: DaysList) {

    val visible = remember { mutableStateOf(false) }

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
        HorizontalPager(pageCount = value.days.size, modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.Top) { page ->
            PairLayout(day = value.days[page])
            DayItem(day = value.days[page])
        }
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize(),
//            contentPadding = PaddingValues(top = 17.dp)
//        ) {
//            items(value.days) {
//                DayItem(it)
//            }
//        }
    }
    LaunchedEffect(key1 = visible, block = {
        delay(150)
        visible.value = true
    })
}
