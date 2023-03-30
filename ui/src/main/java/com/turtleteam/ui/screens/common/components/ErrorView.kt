package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontQanelas
import com.turtleteam.ui.screens.common.views.GradientButton

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    onRefresh: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Не удалось загрузить",
            fontFamily = fontQanelas,
            color = Color.Gray,
        )
        GradientButton(
            gradient = TurtleTheme.color.toolbarGradient,
            radius = 10.dp,
            indicationColor = TurtleTheme.color.secondText,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 30.dp),
            onClick = onRefresh
        ) {
            Text(
                text = "Повторить",
                fontFamily = fontQanelas,
                color = TurtleTheme.color.btnDoneText,
                fontSize = 18.sp
            )
        }
    }
}
