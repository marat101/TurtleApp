package com.turtleteam.ui.utils.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.utils.indications.SelectButtonIndicator

@Composable
fun GradientButton(
    gradient: Brush,
    modifier: Modifier = Modifier,
    indicationColor: Color = Color.Transparent,
    onClick: () -> Unit = { },
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .background(gradient, RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                interactionSource = MutableInteractionSource(),
                indication = SelectButtonIndicator(indicationColor, 12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}