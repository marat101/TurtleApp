package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.R
import com.turtleteam.ui.screens.common.views.GradientButton
import com.turtleteam.ui.theme.*
import com.turtleteam.ui.utils.indications.SelectButtonIndicator

@Composable
fun ScheduleSelectFrame(
    imageId: Int,
    onOpenList: () -> Unit,
    onNextClick: (name: String) -> Unit,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(258.dp)
            .padding(horizontal = 32.dp)
            .background(TurtleTheme.color.transparentBackground, TurtleTheme.shapes.medium)
            .padding(horizontal = 23.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.height(83.dp),
            painter = painterResource(id = imageId),
            contentDescription = null
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .background(LocalColors.current.buttonSelectBackground, LocalShapes.current.medium)
                .border(
                    1.dp,
                    if (!LocalTheme.current) LocalColors.current.textColor.copy(0.35F) else Color.Transparent, LocalShapes.current.medium
                )
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = SelectButtonIndicator(TurtleTheme.color.secondText, 12.dp),
                    onClick = onOpenList
                )
                .clip(TurtleTheme.shapes.medium)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.btn_select_image),
                tint = LocalColors.current.buttonSelectTurtle,
                contentDescription = null
            )
            Text(
                text = name,
                color = LocalColors.current.numberBackground,
                fontSize = 22.sp,
                fontFamily = fontQanelas
            )
        }
        GradientButton(
            gradient = LocalColors.current.buttonNextBackground,
            radius = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            indicationColor = TurtleTheme.color.secondText,
            onClick = { onNextClick(name) }
        ) {
            Text(
                text = "ДАЛЕЕ",
                color = TurtleTheme.color.btnDoneText,
                fontSize = 20.sp,
                fontFamily = fontQanelas
            )
        }
    }
}
