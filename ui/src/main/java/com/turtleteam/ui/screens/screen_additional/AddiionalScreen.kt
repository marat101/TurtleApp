package com.turtleteam.ui.screens.screen_additional

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@Composable
fun AdditionalScreen() {

    val modifier = Modifier
        .size(120.dp)
        .background(TurtleTheme.color.transparentBackground, TurtleTheme.shapes.medium)
        .clip(TurtleTheme.shapes.medium)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(),
                    onClick = {}
                )
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(start = 7.dp, bottom = 25.dp),
                    painter = painterResource(id = R.drawable.ic_call_schedule),
                    contentDescription = null,
                    tint = TurtleTheme.color.moreScreenIconsTint
                )

                Text(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    text = "Расписание звонков",
                    fontFamily = fontGanelas,
                    fontSize = 14.sp,
                    color = TurtleTheme.color.simpleText
                )
            }
            Box(modifier = modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                onClick = {}
            )) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 15.dp, end = 15.dp, start = 15.dp, bottom = 38.dp),
                    painter = painterResource(id = R.drawable.ic_googlesheets),
                    contentDescription = null,
                    tint = TurtleTheme.color.moreScreenIconsTint
                )
            }
        }
        Box(modifier = modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                onClick = {}
            )
            .padding(5.dp)) {
            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 20.dp, start = 5.dp, end = 5.dp),
                painter = painterResource(id = R.drawable.ic_turtle_team),
                contentDescription = null,
            )
        }
    }
}