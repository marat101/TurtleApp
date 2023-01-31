package com.turtleteam.ui


import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.turtleteam.ui.theme.fontFamily

@Composable
fun TextWithFont(modifier: Modifier = Modifier,text:String,color: Color,textSize: TextUnit){
    Text(modifier = modifier,text = text, fontFamily = fontFamily, color = color, fontSize = textSize)
}