package com.turtleteam.ui.screens.common.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.findRootCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.theme_animator.LocalThemeAnimator
import com.turtleteam.ui.theme_animator.getPositiion
import ru.turtleteam.theme.TurtleTheme
import ru.turtleteam.theme.fontQanelas

@Composable
fun TopBar(
    title: String
) {
    val theme = LocalThemeAnimator.current
    var btnCoords by remember { mutableStateOf(Offset.Zero) }
    val insets = WindowInsets.statusBars.asPaddingValues(LocalDensity.current)
    TopAppBar(
        title = {
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = title,
            color = TurtleTheme.color.btnDoneText,
            fontFamily = fontQanelas,
            fontSize = 23.sp
        )
    },
        modifier = Modifier
            .background(TurtleTheme.color.toolbarGradient)
            .padding(insets)
            .height(60.dp),
        contentColor = Color.White,
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        actions = {
            IconButton(modifier = Modifier
                .padding(top = 4.dp)
                .getPositiion {
                    btnCoords = it
                }, onClick = {
                theme.changeTheme(btnCoords, tween(1000))
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
