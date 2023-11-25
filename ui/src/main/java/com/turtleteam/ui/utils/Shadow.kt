package com.turtleteam.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max

fun Modifier.drawShadow(
    shape: Shape = RectangleShape,
    elevation: Dp = 0.dp,
    padding: PaddingValues = PaddingValues(),
    ignoreElevation: Boolean = false
): Modifier = composed {
    with(LocalDensity.current) {
        val paddingValues = if (ignoreElevation) padding else PaddingValues(
            top = max(padding.calculateTopPadding(), elevation),
            start = max(padding.calculateLeftPadding(LocalLayoutDirection.current), elevation),
            end = max(padding.calculateRightPadding(LocalLayoutDirection.current), elevation),
            bottom = max(padding.calculateBottomPadding(), elevation)
        )
        this@composed
            .drawBehind {
                val shadowPaint = Paint().apply {
                    blendMode = BlendMode.SrcOut
                    color = Color.Transparent
                }
                shadowPaint
                    .asFrameworkPaint()
                    .setShadowLayer(
                        elevation.toPx(),
                        0f,
                        0f,
                        Color.Black
                            .copy(0.2f)
                            .toArgb()
                    )
                inset(
                    left = paddingValues
                        .calculateLeftPadding(layoutDirection)
                        .toPx(),
                    top = paddingValues
                        .calculateTopPadding()
                        .toPx(),
                    right = paddingValues
                        .calculateRightPadding(layoutDirection)
                        .toPx(),
                    bottom = paddingValues
                        .calculateBottomPadding()
                        .toPx()
                ) {
                    drawContext.canvas.drawOutline(
                        shape.createOutline(size, layoutDirection, this@with),
                        shadowPaint
                    )
                }
            }
            .padding(paddingValues)
    }
}

@Preview
@Composable
private fun ShadowPreview(
    shape: Shape = RoundedCornerShape(12.dp)
){
    Box(Modifier.background(Color.White)) {
        Box(modifier = Modifier
            .size(15.dp)
            .background(Color.Red,CircleShape)
            .align(Alignment.Center))
        Box(modifier = Modifier.fillMaxSize().drawShadow(shape,6.dp).background(Color.Blue.copy(0.4f), shape))
    }
}