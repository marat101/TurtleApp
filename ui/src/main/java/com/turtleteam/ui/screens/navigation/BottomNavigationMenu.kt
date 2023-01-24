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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.lightBackgroundBrush

@Composable
fun BottomNavigationMenu(
    navHostController: NavHostController
){
    val backStack = navHostController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = Modifier.fillMaxWidth().background(lightBackgroundBrush),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        val isHomeScreenSelected = backStack.value?.destination?.route == Routes.HOME_SCREEN.route
        CustomBottomNavigationItem(
            rowScope = this,
            isItemSelected = isHomeScreenSelected,
            navHostController = navHostController,
            route = Routes.HOME_SCREEN.route,
            drawableId = R.drawable.ic_groups,
            stringId = R.string.groups
        )
        val isTeachersScreenSelected = backStack.value?.destination?.route == Routes.TEACHERS_SCREEN.route
        CustomBottomNavigationItem(
            rowScope = this,
            isItemSelected = isTeachersScreenSelected,
            navHostController = navHostController,
            route = Routes.TEACHERS_SCREEN.route,
            drawableId = R.drawable.ic_teachers,
            stringId = R.string.teachers
        )

        val isMoreScreenSelected = backStack.value?.destination?.route == Routes.MORE_SCREEN.route
        CustomBottomNavigationItem(
            rowScope = this,
            isItemSelected = isMoreScreenSelected,
            navHostController = navHostController,
            route = Routes.MORE_SCREEN.route,
            drawableId = R.drawable.ic_more,
            stringId = R.string.more
        )
    }
}

@Composable
fun CustomBottomNavigationItem(
    rowScope: RowScope,
    isItemSelected: Boolean,
    navHostController: NavHostController,
    route:String,
    @DrawableRes drawableId:Int,
    @StringRes stringId:Int
) {
    rowScope.BottomNavigationItem(
        selected = isItemSelected,
        onClick = {
            navHostController.navigate(route){ popUpTo(0) }
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
    @DrawableRes drawableId:Int,
    @StringRes stringId:Int,
    isItemSelected:Boolean
){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = drawableId),
            contentDescription = null,
            tint = if (isItemSelected) Color.White else Color.Black
        )
        Text(
            text = stringResource(id = stringId),
            fontSize = if (isItemSelected) 14.sp else 10.sp,
            color = if (isItemSelected) Color.White else Color.Black
        )
    }
}