package com.turtleteam.ui.screens.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.JetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationMenu(
    pagerState: PagerState,
    backStack: State<NavBackStackEntry?>
) {
    val coroutine = rememberCoroutineScope()
    if (backStack.value?.destination?.route != Routes.MAIN_PAGER_SCREEN.route) return
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .background(JetTheme.color.bottomNavBarGradient),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        val isHomeTabSelected = pagerState.currentPage == 0
        CustomBottomNavigationItem(
            rowScope = this,
            isItemSelected = isHomeTabSelected,
            pagerState = pagerState,
            pageId = 0,
            coroutine = coroutine,
            drawableId = R.drawable.ic_groups,
            stringId = R.string.groups
        )
        val isTeachersTabSelected = pagerState.currentPage == 1
        CustomBottomNavigationItem(
            rowScope = this,
            isItemSelected = isTeachersTabSelected,
            pagerState = pagerState,
            pageId = 1,
            coroutine = coroutine,
            drawableId = R.drawable.ic_teachers,
            stringId = R.string.teachers
        )
        val isMoreTabSelected = pagerState.currentPage == 2
        CustomBottomNavigationItem(
            rowScope = this,
            isItemSelected = isMoreTabSelected,
            pagerState = pagerState,
            pageId = 2,
            coroutine = coroutine,
            drawableId = R.drawable.ic_more,
            stringId = R.string.more
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomBottomNavigationItem(
    rowScope: RowScope,
    isItemSelected: Boolean,
    pagerState: PagerState,
    pageId:Int,
    coroutine: CoroutineScope,
    @DrawableRes drawableId: Int,
    @StringRes stringId: Int
) {
    rowScope.BottomNavigationItem(
        selected = isItemSelected,
        onClick = {
            coroutine.launch{
                pagerState.animateScrollToPage(pageId)
            }
        },
        icon = {
            BottomNavigationItemIcon(
                drawableId = drawableId,
                stringId = stringId,
                isItemSelected = isItemSelected
            )
        }
    )
}

@Composable
fun BottomNavigationItemIcon(
    @DrawableRes drawableId: Int,
    @StringRes stringId: Int,
    isItemSelected: Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = drawableId),
            contentDescription = null,
            tint = JetTheme.color.bottomNavMenuColors.getColor(isItemSelected)
        )
        Text(
            text = stringResource(id = stringId),
            fontSize = if (isItemSelected) 14.sp else 10.sp,
            color =  JetTheme.color.bottomNavMenuColors.getColor(isItemSelected)
        )
    }
}