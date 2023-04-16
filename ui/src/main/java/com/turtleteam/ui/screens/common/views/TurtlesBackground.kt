package com.turtleteam.ui.screens.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.LocalColors
import com.turtleteam.ui.theme.TurtleTheme

@Composable
fun TurtlesBackground() {

    val tint = LocalColors.current.backgroundTurtle

    Box(
        Modifier
            .fillMaxSize()
            .background(TurtleTheme.color.backgroundBrush)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(60.dp, 33.dp)
                .offset(x = 24.dp, y = (-111).dp),
            painter = painterResource(id = R.drawable.turtle_right),
            contentDescription = "",
            tint = tint
        )
        Icon(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(37.dp, 20.dp)
                .offset(x = (-35).dp, y = (-70).dp),
            painter = painterResource(id = R.drawable.turtle_right),
            contentDescription = "",
            tint = tint
        )
        Icon(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(60.dp, 33.dp)
                .offset(x = (-10).dp, y = (-149).dp),
            painter = painterResource(id = R.drawable.turtle_right),
            contentDescription = "",
            tint = tint
        )
        Icon(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(73.dp, 40.dp)
                .offset(x = (-24).dp, y = (-92).dp),
            painter = painterResource(id = R.drawable.turtle_left),
            contentDescription = "",
            tint = tint
        )
        Icon(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(82.dp, 45.dp)
                .offset(x = (16).dp, y = 171.dp),
            painter = painterResource(id = R.drawable.turtle_left),
            contentDescription = "",
            tint = tint
        )
        Icon(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(103.dp, 57.dp)
                .offset(x = (-50).dp, y = 52.dp),
            painter = painterResource(id = R.drawable.turtle_left),
            contentDescription = "",
            tint = tint
        )
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(45.dp, 25.dp)
                .offset(x = (-20).dp, y = 158.dp),
            painter = painterResource(id = R.drawable.turtle_right),
            contentDescription = "",
            tint = tint
        )
    }
}