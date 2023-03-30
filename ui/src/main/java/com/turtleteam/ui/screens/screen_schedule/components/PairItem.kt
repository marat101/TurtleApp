package com.turtleteam.ui.screens.screen_schedule.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.PairsList
import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.ISO8601
import com.soywiz.klock.parse
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.fontQanelas

@Composable
fun PairItem(pairs: PairsList) {
    pairs.apair.forEach { item ->
        Column(
            modifier = Modifier
                .height(180.dp)
                .width(330.dp)
                .background(color = Color(0xFFF5F6F1), shape = RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 23.dp, end = 11.dp, top = 14.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Row(
                    modifier = Modifier
                        .size(25.dp)
                        .background(color = Color(0xFF417B65), shape = CircleShape)
                        .padding(end = 23.dp)
                ) {
                    Text(
                        text = item.number.toString(), style = TextStyle(
                            color = Color.White,
                            fontFamily = fontQanelas,
                            fontSize = 20.sp,
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .width(246.dp)
                        .background(color = Color(0xFFA7CE7B), shape = RoundedCornerShape(30.dp))
                ) {
                    Text(
                        text = item.doctrine,
                        style = TextStyle(
                            color = Color(0xFF417B65),
                            fontFamily = fontQanelas,
                            fontSize = if (item.doctrine.length < 25) 16.sp else 14.sp
                        ),
                        modifier = Modifier.padding(
                            top = 14.dp,
                            bottom = 14.dp,
                            end = 16.dp,
                            start = 16.dp
                        )
                    )
                }
            }

            val formatter = DateFormat("yyyy-MM-dd'T'HH:mm:ss")
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 11.dp)) {
                Column(Modifier.padding(start = 15.dp, end = 22.dp)) {
                    Text(text = formatter.parse(pairs.isoDateStart).toString("HH:mm"))
                    Text(text = formatter.parse(pairs.isoDateEnd).toString("HH:mm"))
                }
                Column {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_teachers),
                            contentDescription = "",
                            tint = Color(0xFF9E9C9F),
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 10.dp)
                        )
                        Text(
                            text = item.teacher, style = TextStyle(
                                fontFamily = fontQanelas,
                                fontSize = 14.sp,
                                color = Color(0xFF9E9C9F)
                            )
                        )
                    }
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_teachers),
                            contentDescription = "",
                            tint = Color(0xFF9E9C9F),
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 10.dp)
                        )
                        Text(text = item.auditoria, style = TextStyle(
                            fontFamily = fontQanelas,
                            fontSize = 14.sp,
                            color = Color(0xFF9E9C9F)
                        ))
                    }
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_teachers),
                            contentDescription = "",
                            tint = Color(0xFF9E9C9F),
                            modifier = Modifier
                                .size(25.dp)
                                .padding(end = 10.dp)
                        )
                        Text(text = item.corpus, style = TextStyle(
                            fontFamily = fontQanelas,
                            fontSize = 14.sp,
                            color = Color(0xFF9E9C9F)
                        ))
                    }
                }
            }
        }
    }
}
