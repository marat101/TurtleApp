package com.turtleteam.ui.screens.navigation

import androidx.compose.foundation.background
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
import com.turtleteam.ui.theme.JetTheme

@Composable
fun TopBar(
    text: String,
    switchTheme:() -> Unit
) {
    TopAppBar(title = {
        Text(
            text = text, color = JetTheme.color.btnDoneText
        )
    },
        modifier = Modifier.background(JetTheme.color.toolbarGradient),
        contentColor = Color.White,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(onClick = {
                switchTheme()
            }) {
                Icon(
                    modifier = Modifier.size(24.dp), painter = painterResource(
                        id = JetTheme.images.topBarIcon
                    ), contentDescription = null
                )
            }
        }
    )
}