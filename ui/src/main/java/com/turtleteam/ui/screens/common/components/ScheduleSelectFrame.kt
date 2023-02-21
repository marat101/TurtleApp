package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas
import com.turtleteam.ui.utils.indications.SelectButtonIndicator
import com.turtleteam.ui.utils.views.GradientButton

@Composable
fun ScheduleSelectFrame(
    imageId: Int,
    onOpenList: () -> Unit,
    onNextClick: () -> Unit,
    name: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 15.dp)
            .background(TurtleTheme.color.transparentBackground, TurtleTheme.shapes.medium)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageId), contentDescription = null)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.clickable(
                interactionSource = MutableInteractionSource(),
                indication = SelectButtonIndicator(TurtleTheme.color.secondText, 10.dp),
                onClick = onOpenList
            )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(TurtleTheme.shapes.medium),
                painter = painterResource(id = TurtleTheme.images.btnSelect),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                text = name,
                color = TurtleTheme.color.btnGroupTeacherText,
                fontSize = 27.sp,
                fontFamily = fontGanelas
            )
        }
        GradientButton(
            gradient = TurtleTheme.color.toolbarGradient,
            radius = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            indicationColor = TurtleTheme.color.secondText,
            onClick = onNextClick
        ) {
            Text(
                text = "ДАЛЕЕ",
                color = TurtleTheme.color.btnDoneText,
                fontSize = 22.sp,
                fontFamily = fontGanelas
            )
        }
    }
}