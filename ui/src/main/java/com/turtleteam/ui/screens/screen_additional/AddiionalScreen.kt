package com.turtleteam.ui.screens.screen_additional

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.R
import com.turtleteam.ui.screens.screen_additional.components.Item
import com.turtleteam.ui.theme.TurtleTheme
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
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                modifier = Modifier
                    .size(89.dp)
                        //TODO colors
                    .background(TurtleTheme.color.nameItemBackground.copy(alpha = 0.33F), CircleShape)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_turtle),
                contentDescription = null
            )
        }
        item {

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
