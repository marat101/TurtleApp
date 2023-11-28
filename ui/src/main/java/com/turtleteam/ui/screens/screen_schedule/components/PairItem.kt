package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.Pair
import com.android.turtleapp.data.model.schedule.PairsList
import com.turtleteam.ui.utils.drawShadow
import com.turtleteam.ui.utils.extensions.toDate
import ru.turtleteam.theme.LocalColors
import ru.turtleteam.theme.LocalShapes
import ru.turtleteam.theme.R


@Composable
fun PairItem(pairs: PairsList, scrollInProgress: Boolean) {
    val currentMillis = System.currentTimeMillis()
    val startMillis = pairs.isoDateStart.toDate().time
    val endMillis = pairs.isoDateEnd.toDate().time

    Box {
        if (currentMillis in (startMillis + 1) until endMillis) {
            val default = endMillis - startMillis
            val progress = (currentMillis - startMillis).toFloat()
            val end = default - progress
            CurrentPair(progress, end, pairs, scrollInProgress)
        } else {
            Pair(pairs, scrollInProgress)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.CurrentPair(progress: Float, end: Float, pairs: PairsList, scrollInProgress: Boolean) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // provide pageCount
        pairs.apair.size
    }
    val progressColor = LocalColors.current.numberBackground
    val endColor = LocalColors.current.pairInfo
    var height by remember { mutableStateOf(0) }
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Column(
            modifier = Modifier
                .width(71.dp)
                .height(with(LocalDensity.current) { height.toDp() })
                .padding(start = 16.dp,top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                fontSize = 20.sp,
                text = pairs.apair.first().start,
                color = progressColor,
            )
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .background(progressColor)
                    .weight(progress)
            )
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .weight(end)
                    .background(endColor)
            )
            Text(
                fontSize = 20.sp,
                text = pairs.apair.first().end,
                color = endColor,
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .drawShadow(
                    shape = LocalShapes.current.medium,
                    elevation = 4.dp,
                    padding = PaddingValues(start = 0.dp, end = 16.dp, bottom = 8.dp, top = 4.dp),
                )
                .background(
                    LocalColors.current.baseItemBackground.copy(0.75f),
                    LocalShapes.current.medium
                )
                .padding(start = 8.dp)
                .onGloballyPositioned {
                    height = it.size.height
                }
        ) {
            if (pairs.apair.size > 1)
                HorizontalPager(
                    userScrollEnabled = !scrollInProgress,
                    state = pagerState,
                    contentPadding = PaddingValues(start = 8.dp),
                    flingBehavior = PagerDefaults.flingBehavior(
                        state = pagerState,
                        lowVelocityAnimationSpec = tween(
                            easing = LinearEasing,
                            durationMillis = 200
                        )
                    )
                ) {
                    PairInfo(pair = pairs.apair[it])
                }
            else
                PairInfo(pair = pairs.apair.first())
        }
    }

    Text(
        modifier = Modifier
            .padding(bottom = 21.dp, end = 27.dp)
            .size(25.dp)
            .background(LocalColors.current.numberBackground, CircleShape)
            .align(Alignment.BottomEnd),
        fontSize = 20.sp,
        text = pairs.apair.first().number.toString(),
        color = Color.White,
        textAlign = TextAlign.Center
    )

    if (pairs.apair.size > 1)
        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            count = pairs.apair.size,
            current = pagerState.currentPage
        )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxScope.Pair(pairs: PairsList, scrollInProgress: Boolean) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
         pairs.apair.size
    }

    Row(
        Modifier
            .fillMaxSize()
            .drawShadow(
                shape = LocalShapes.current.medium,
                elevation = 4.dp,
                padding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 4.dp),
            )
            .background(
                LocalColors.current.baseItemBackground.copy(0.75f),
                LocalShapes.current.medium
            )
    ) {
        Column(modifier = Modifier.width(71.dp), horizontalAlignment = Alignment.End) {
            Text(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(25.dp)
                    .background(LocalColors.current.numberBackground, CircleShape)
                    .align(Alignment.CenterHorizontally),
                fontSize = 20.sp,
                text = pairs.apair.first().number.toString(),
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .padding(top = 3.dp, end = 6.dp),
                fontSize = 20.sp,
                text = pairs.apair.first().start,
                color = LocalColors.current.numberBackground,
            )
            Text(
                modifier = Modifier
                    .padding(end = 8.dp),
                fontSize = 14.sp,
                text = pairs.apair.first().end,
                color = LocalColors.current.pairInfo,
            )
        }
        if (pairs.apair.size > 1)
            HorizontalPager(
                userScrollEnabled = !scrollInProgress,
                state = pagerState,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState,
                    lowVelocityAnimationSpec = tween(
                        easing = LinearEasing,
                        durationMillis = 200
                    )
                )
            ) {
                PairInfo(pair = pairs.apair[it])
            }
        else
            PairInfo(pair = pairs.apair.first())
    }
    if (pairs.apair.size > 1)
        PageIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp),
            count = pairs.apair.size,
            current = pagerState.currentPage
        )
}

@Composable
fun PairInfo(modifier: Modifier = Modifier,pair: Pair) {
    Column(
        Modifier
            .padding(end = 11.dp)
            .padding(vertical = 14.dp)
            .then(modifier)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp)
                .background(LocalColors.current.doctrineBackground, RoundedCornerShape(35.dp))
                .padding(horizontal = 15.dp, vertical = 6.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = pair.doctrine,
                fontSize = if (pair.doctrine.length > 20) 14.sp else 18.sp,
                color = LocalColors.current.textColor,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 11.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_teachers),
                contentDescription = "",
                tint = LocalColors.current.pairInfo
            )
            Text(
                text = pair.teacher,
                fontSize = 14.sp,
                color = LocalColors.current.pairInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_auditorium),
                contentDescription = "",
                tint = LocalColors.current.pairInfo
            )
            Text(
                text = pair.auditoria,
                fontSize = 14.sp,
                color = LocalColors.current.pairInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_corpus),
                contentDescription = "",
                tint = LocalColors.current.pairInfo
            )
            Text(
                text = pair.corpus,
                fontSize = 14.sp,
                color = LocalColors.current.pairInfo,
            )
        }
    }
}

@Composable
fun PageIndicator(modifier: Modifier = Modifier, count: Int, current: Int) {
    val enabled = LocalColors.current.numberBackground
    val disabled = LocalColors.current.pairInfo

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        repeat(count) { iteration ->
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(if (current == iteration) enabled else disabled)
            })
        }
    }
}