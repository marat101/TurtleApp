package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.PairsList
import com.soywiz.klock.DateFormat
import com.soywiz.klock.parse
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.fontQanelas


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PairItem(pairs: PairsList, slider: Boolean? = false, pagerState: PagerState? = null) {
    pairs.apair.forEach { item ->
        Card(
            modifier = Modifier
                .width(330.dp)
//                .background(Color(0xFFF5F6F1).copy(0.76f))
                .clip(RoundedCornerShape(12.dp)),
            elevation = 4.dp,
            backgroundColor = Color(0xFFF5F6F1),
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 14.dp, bottom = 14.dp, start = 23.dp, end = 11.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.number.toString(), style = TextStyle(
                            color = Color.White,
                            fontFamily = fontQanelas,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier
                            .size(25.dp)
                            .background(color = Color(0xFF417B65), shape = CircleShape)
                    )

                    Text(
                        text = item.doctrine,
                        style = TextStyle(
                            color = Color(0xFF417B65),
                            fontFamily = fontQanelas,
                            fontSize = if (item.doctrine.length < 25) 16.sp else 14.sp
                        ),
                        modifier = Modifier
                            .width(246.dp)
                            .background(
                                color = Color(0xFFA7CE7B).copy(0.2f),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .padding(vertical = 14.dp, horizontal = 16.dp)
                    )
                }

                val formatter = DateFormat("yyyy-MM-dd'T'HH:mm:ss")
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 11.dp)
                ) {
                    Column(
                        Modifier.padding(start = 15.dp, end = 22.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = formatter.parse(pairs.isoDateStart).toString("HH:mm"),
                            style = TextStyle(
                                color = Color(0xFF417B65),
                                fontSize = 20.sp,
                                fontFamily = fontQanelas
                            )
                        )
                        Text(
                            text = formatter.parse(pairs.isoDateEnd).toString("HH:mm"),
                            style = TextStyle(
                                color = Color(0xFF9E9C9F),
                                fontSize = 14.sp,
                                fontFamily = fontQanelas
                            )
                        )
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
                                painter = painterResource(id = R.drawable.ic_auditorium),
                                contentDescription = "",
                                tint = Color(0xFF9E9C9F),
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = 10.dp)
                            )
                            Text(
                                text = item.auditoria, style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontSize = 14.sp,
                                    color = Color(0xFF9E9C9F)
                                )
                            )
                        }
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_corpus),
                                contentDescription = "",
                                tint = Color(0xFF9E9C9F),
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = 10.dp)
                            )
                            Text(
                                text = item.corpus, style = TextStyle(
                                    fontFamily = fontQanelas,
                                    fontSize = 14.sp,
                                    color = Color(0xFF9E9C9F)
                                )
                            )
                        }
                    }
                    if (slider == true){
                        Row(
                            Modifier
                                .height(50.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(2) { iteration ->
                                val color = if (pagerState?.currentPage == iteration) Color.DarkGray else Color.LightGray
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(20.dp)

                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

