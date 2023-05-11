package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.turtleteam.theme.TurtleTheme
import ru.turtleteam.theme.fontQanelas

@Composable
fun TopBar(
    onThemeChange: () -> Unit,
    title: String
) {
    TopAppBar(title = {
        Text(modifier = Modifier.padding(top = 4.dp),
            text = title,
            color = TurtleTheme.color.btnDoneText,
            fontFamily = fontQanelas,
            fontSize = 23.sp
        )
    },
        modifier = Modifier.background(TurtleTheme.color.toolbarGradient).height(60.dp),
        contentColor = Color.White,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(modifier = Modifier.padding(top = 4.dp),onClick = {
                onThemeChange()
            }) {
                Icon(
                    tint = TurtleTheme.color.themeChangeButton,
                    modifier = Modifier.size(26.dp),
                    painter = painterResource(id = TurtleTheme.images.topBarIcon),
                    contentDescription = null
                )
            }
        }
    )
}
