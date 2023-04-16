package com.turtleteam.ui.screens.common.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontQanelas

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 18.sp,
    fontFamily: FontFamily = fontQanelas,
) {

    BasicTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        singleLine = true,
        textStyle = TextStyle(
            color = TurtleTheme.color.secondText,
            fontFamily = fontFamily,
            fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.padding(horizontal = 3.dp)) {
                    Text(
                        text = if (value.isEmpty()) placeholder else "",
                        fontFamily = fontFamily,
                        color = Color.Gray,
                        fontSize = fontSize
                    )
                    innerTextField()
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    color = Color.DarkGray
                )
            }
        }
    )
}
