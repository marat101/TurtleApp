package com.turtleteam.ui.screens.navigation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.turtleteam.theme.R
import com.turtleteam.ui.screens.screen_home.HomeViewModel
import ru.turtleteam.theme.TurtleTheme
import ru.turtleteam.theme.fontQanelas

@Composable
fun BottomNavigationMenu(
    viewModel: HomeViewModel,
    currentPage: Int,
) {

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .background(TurtleTheme.color.bottomNavBarGradient),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        BottomNavigationItem(
            selected = currentPage == 0,
            onClick = { viewModel.navigateToGroups() },
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
                    fontFamily = fontQanelas
                )
            })
        BottomNavigationItem(
            selected = currentPage == 1,
            onClick = { viewModel.navigateToTeachers() },
            selectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(true),
            unselectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(false),
            icon = {
                Icon(
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp),
                    //TODO
                    painter = painterResource(id = R.drawable.ic_teachers),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Преподаватели",
                    fontSize = 11.sp,
                    fontFamily = fontQanelas,
                    fontWeight = FontWeight(800)
                )
            })
        BottomNavigationItem(
            selected = currentPage == 2,
            onClick = { viewModel.navigateToAdditional()},
            selectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(true),
            unselectedContentColor = TurtleTheme.color.bottomNavMenuColors.getColor(false),
            icon = {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.ic_additional),
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Дополнительно",
                    fontSize = 11.sp,
                    fontFamily = fontQanelas
                )
            })
    }
}