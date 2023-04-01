package com.turtleteam.ui.screens.screen_additional

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.usecases_impl.usersettings.GetCallsScheduleUseCase
import com.turtleteam.ui.R
import com.turtleteam.ui.screens.screen_additional.components.CallsList
import com.turtleteam.ui.screens.screen_additional.components.Item
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.utils.PagerListener
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.get

//TODO viewmodel

@Composable
fun AdditionalScreen(
    page: Int = 0,
    pageListener: PagerListener = get(),
    usecase: GetCallsScheduleUseCase = get()
) {

    var isVisible by remember { mutableStateOf(false) }
    val calls = remember { usecase.execute() }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                modifier = Modifier
                    .padding(top = 9.dp, bottom = 15.dp)
                    .size(89.dp)
                    //TODO colors
                    .background(TurtleTheme.color.nameItemBackground, CircleShape)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_turtle),
                contentDescription = null
            )
        }
        item {
            Item("Расписание звонков", expanded = isVisible, onClick = {
                isVisible = !isVisible
            })
        }

        if (isVisible)
            item {
                if (isVisible)
                    CallsList(calls, onBackPress = {
                        isVisible = false
                    })
            }

        item {
            Item("Планшетка", {})
        }
        item {
            Item("Данные преподавателей", {})
        }
    }
    LaunchedEffect(key1 = pageListener, block = {
        pageListener.getPageListener(page).collectLatest {
            if (!it)
                isVisible = it
        }
    })
}
