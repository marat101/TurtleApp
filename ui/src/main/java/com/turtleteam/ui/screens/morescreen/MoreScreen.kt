package com.turtleteam.ui.screens.morescreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turtleteam.ui.R
import com.turtleteam.ui.TextWithFont
import com.turtleteam.ui.screens.navigation.Routes
import com.turtleteam.ui.theme.JetTheme

@Composable
fun MoreScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            MoreScreenOneItem(
                drawableId = R.drawable.ic_call_schedule,
                stringResource(R.string.schedule_list),
                JetTheme.color.moreScreenIconsTint
            ) {
                navController.navigate(Routes.SCHEDULE_LIST.route)
            }
            MoreScreenOneItem(
                drawableId = R.drawable.ic_googlesheets,
                stringResource(R.string.tablet),
                JetTheme.color.moreScreenIconsTint
            ) {
                //todo click
            }
        }
        MoreScreenOneItem(
            drawableId = R.drawable.ic_turtle_team,
            stringResource(R.string.turtle_team)
        ) {
            //todo click
        }
    }
}

@Composable
fun MoreScreenOneItem(
    @DrawableRes drawableId: Int,
    text: String,
    tint:Color = Color.Unspecified,
    onClickAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(130.dp)
            .clickable(onClick = onClickAction)
            .padding(10.dp)
            .background(JetTheme.color.transparentBackground, RoundedCornerShape(8.dp))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.weight(1f),
            painter = painterResource(id = drawableId),
            contentDescription = null,
            tint = tint
        )
        TextWithFont(text = text, color = Color.Gray, textSize = 12.sp, align = TextAlign.Center)
    }
}