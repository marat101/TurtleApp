package com.turtleteam.ui.screens.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.utils.indications.SelectButtonIndicator

@Composable
fun GradientButton(
    gradient: Brush,
    modifier: Modifier = Modifier,
    indicationColor: Color = Color.Transparent,
    radius: Dp,
    onClick: () -> Unit = { },
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(gradient, RoundedCornerShape(radius))
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = SelectButtonIndicator(indicationColor, radius)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}