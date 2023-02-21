package com.turtleteam.ui.screens.navigation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.TurtleTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationMenu(
//    pagerState: PagerState = rememberPagerState(),
//    backStack: State<NavBackStackEntry?>
) {
//    val coroutine = rememberCoroutineScope()
//    if (backStack.value?.destination?.route != Routes.HOME_PAGER_SCREEN.name) return
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(TurtleTheme.color.bottomNavBarGradient),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        BottomNavigationItem(selected = true, onClick = { /*TODO*/ }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_groups),
                contentDescription = null
            )
        },
            label = { Text(text = "aaaaa")})
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_teachers),
                contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        },
            label = { Text(text = "aaaaa")})
        BottomNavigationItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = null,
            )
        },
        label = { Text(text = "aaaaa")})
    }
}

