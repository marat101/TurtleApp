package com.turtleteam.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun TiledButton(
    onClick: () -> Unit,
    @DrawableRes backgroundDrawableId: Int,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    contentColor: Color = MaterialTheme.colors.primary,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    Box(modifier.clickable { onClick() }, contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = backgroundDrawableId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
}