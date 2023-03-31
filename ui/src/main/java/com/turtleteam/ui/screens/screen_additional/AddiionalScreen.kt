package com.turtleteam.ui.screens.screen_additional

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.turtleteam.ui.utils.PagerListener
import org.koin.androidx.compose.get

//TODO viewmodel

@Composable
fun AdditionalScreen(
    page: Int,
    pageListener: PagerListener = get()
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Image(modifier = Modifier.size(89.dp),painter = painterResource(id = R.drawable.ic_turtle), contentDescription = null)
        }
        item {
            Item("Расписание звонков")
        }
        item {
            Item("Планшетка")
        }
        item {
            Item("Данные преподавателей")
        }
    }
}

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