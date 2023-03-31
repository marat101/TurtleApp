package com.turtleteam.ui.screens.screen_additional.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas


@Composable
fun Item(text: String) {
    Row(
        modifier = Modifier
            .height(61.dp)
            .fillMaxWidth()
            .background(TurtleTheme.color.transparentBackground, RoundedCornerShape(12.dp))
            .padding(start = 15.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontFamily = fontGanelas,
            fontWeight = FontWeight(700),
            fontSize = 22.sp,
            color = TurtleTheme.color.secondText
        )
        Icon(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = null,
            tint = TurtleTheme.color.secondText
        )
    }
}