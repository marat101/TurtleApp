package com.turtleteam.ui.utils.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@Composable
fun BaseTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
) {

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        keyboardActions = keyboardActions,
        textStyle = TextStyle(
            color = TurtleTheme.color.secondText,
            fontFamily = fontGanelas,
            fontSize = 25.sp
        ),
        decorationBox = { innerTextField ->
            Row(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = if (isSystemInDarkTheme()) Color(0xFF969EBD) else Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
            innerTextField()
        }
    )
}