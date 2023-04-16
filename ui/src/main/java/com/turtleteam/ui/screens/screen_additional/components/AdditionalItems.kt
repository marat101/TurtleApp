package com.turtleteam.ui.screens.screen_additional.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.LocalColors
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontQanelas

@Composable
fun Item(text: String,
         onClick: () -> Unit,
         expanded: Boolean = false) {

    val rotation = if (expanded) 90F else 0F
    Button(
        modifier = Modifier
            .height(61.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(4.dp,RoundedCornerShape(12.dp)),
        colors = ButtonDefaults.buttonColors(backgroundColor = TurtleTheme.color.transparentBackground),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
        ),
        contentPadding = PaddingValues(end = 18.dp, start = 15.dp),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.weight(1F),
            text = text,
            fontFamily = fontQanelas,
            fontWeight = FontWeight(700),
            fontSize = 22.sp,
            color = LocalColors.current.textColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            letterSpacing = 0.sp
        )
        Icon(
            modifier = Modifier.size(9.dp, 17.dp).rotate(rotation),
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = LocalColors.current.textColor
        )
    }
}