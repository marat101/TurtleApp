package com.turtleteam.ui.screens.navigation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationMenu(
    selected: PagerState,
) {
//    val coroutine = rememberCoroutineScope()
//    if (backStack.value?.destination?.route != Routes.HOME_PAGER_SCREEN.name) return

    val coroutine = rememberCoroutineScope()

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .background(TurtleTheme.color.bottomNavBarGradient)
            ,
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        BottomNavigationItem(
            selected = selected.currentPage == 0,
            onClick = { coroutine.launch { selected.animateScrollToPage(0) } },
            selectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(true),
            unselectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(false),
            icon = {
                Icon(
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp),
                    painter = painterResource(id = R.drawable.ic_groups),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Группы",
                    fontSize = 14.sp,
                    fontFamily = fontGanelas
                )
            })
        BottomNavigationItem(
            selected = selected.currentPage == 1,
            onClick = { coroutine.launch { selected.animateScrollToPage(1) } },
            selectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(true),
            unselectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(false),
            icon = {
                Icon(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    painter = painterResource(id = R.drawable.ic_teachers),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Преподаватели",
                    fontSize = 11.sp,
                    fontFamily = fontGanelas,
                    fontWeight = FontWeight(800)
                )
            })
        BottomNavigationItem(
            selected = selected.currentPage == 2,
            onClick = { coroutine.launch { selected.animateScrollToPage(2) } },
            selectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(true),
            unselectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(false),
            icon = {
                Icon(
                    modifier = Modifier
                        .height(28.dp)
                        .width(329.dp),
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Дополнительно",
                    fontSize = 11.sp,
                    fontFamily = fontGanelas
                )
            })
    }
}