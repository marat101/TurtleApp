package com.turtleteam.ui.screens.screen_additional

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.remote_database.AppUpdate
import com.turtleteam.ui.screens.screen_additional.components.CallsList
import com.turtleteam.ui.screens.screen_additional.components.Item
import ru.turtleteam.theme.LocalColors
import ru.turtleteam.theme.LocalTheme
import ru.turtleteam.theme.TurtleTheme
import ru.turtleteam.theme.fontQanelas
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import ru.turtleteam.theme.R

@Composable
fun AdditionalScreen(
    page: Int = 0,
    viewModel: AdditionalViewModel = getViewModel(
        parameters = { parametersOf(page) }),
) {

    val state = viewModel.state.collectAsState()
    val imageFilter = LocalColors.current.transparentBackground.copy(0.45F)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier
                    .padding(top = 9.dp, bottom = 15.dp)
                    .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                        viewModel.navigateToTurtleTeamVK()
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(89.dp)
                        .background(LocalColors.current.turtleImageBackground, CircleShape)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.ic_turtle),
                    contentDescription = null,
                    colorFilter = if(LocalTheme.current) ColorFilter.tint(imageFilter, BlendMode.Darken) else null
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Turtle Team",
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight(700),
                    fontSize = 16.sp,
                    color = TurtleTheme.color.secondText,
                    letterSpacing = 0.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        item {
            Item("Расписание звонков", expanded = state.value.isExpandedCalls, onClick = {
                viewModel.clickOnCalls()
            })
        }

        if (state.value.isExpandedCalls)
            item {
                CallsList(state.value.calls) {
                    viewModel.clickOnCalls()
                }
            }

        item {
            Item("Планшетка", {
                viewModel.navigateToGoogleSheet()
            })
        }
        item {
            Item("Данные преподавателей", {})
        }
        if (state.value.updateState is AppUpdate.Success) {
            if ((state.value.updateState as AppUpdate.Success).isUpdateAvaible)
                item {
                    Item("Обновление", {viewModel.clickOnUpdate()})
                }
        }
        item {
            Item(text = "Уведомления", onClick = { viewModel.navigateToNotificationsScreen() })
        }
    }
    LaunchedEffect(key1 = viewModel, block = {
        viewModel.initPageListener()
    })
}
