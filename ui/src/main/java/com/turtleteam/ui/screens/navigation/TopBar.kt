package com.turtleteam.ui.screens.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.turtleteam.domain.usecases.SaveThemeStateUseCase
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.JetTheme

@Composable
fun TopBar(isDarkMode: MutableState<Boolean>,saveThemeStateUseCase: SaveThemeStateUseCase) {
    TopAppBar(
        title = { Text(text = "TurtleApp") },
        modifier = Modifier.background(JetTheme.color.toolbarGradient),
        contentColor = Color.White,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
                isDarkMode.value = !isDarkMode.value
                saveThemeStateUseCase.execute(isDarkMode.value)
            }) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(
                        id = if (!isDarkMode.value)
                            R.drawable.moon
                        else R.drawable.sun
                    ),
                    contentDescription = null
                )
            }
        }
    )
}