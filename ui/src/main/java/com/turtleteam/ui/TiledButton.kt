package com.turtleteam.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        enabled = enabled,
        shape = shape,
        border = border,
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = contentColor,
            disabledBackgroundColor = Color.Transparent,
            disabledContentColor = contentColor.copy(alpha = ContentAlpha.disabled),
        ),
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Image(painter = painterResource(id = backgroundDrawableId), contentDescription = null )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(contentPadding),
                content = content,
            )
        }
    }
}