package com.turtleteam.ui.screens.common.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.utils.SearchNames
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NamesList(
    sheetModelState: States<NamesList>,
    cornersState: MutableState<RoundedCornerShape>,
    sheetState: ModalBottomSheetState,
    getList: () -> Unit,
    onItemClick: (name: String) -> Unit,
    onLongClick: (list: NamesList, item: String) -> Unit,
    onRefreshClick: () -> Unit,
    isTeacher: Boolean
) {

    val state = remember { mutableStateOf(NamesList.empty) }
    val spanSize = if (isTeacher) 4 else 2
    val header = if (isTeacher) "Все преподаватели" else "Все группы"
    val searchState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(TurtleTheme.color.backgroundBrush)
            .padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(5.dp)
                .background(TurtleTheme.color.bottomSheetView, TurtleTheme.shapes.small)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = searchState.value,
            onValueChange = {
                searchState.value = it
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = TurtleTheme.color.secondText,
                cursorColor = Color.Gray,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Gray
            ),
            textStyle = TextStyle(
                fontFamily = fontGanelas,
                fontSize = 25.sp
            )
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            when (sheetModelState) {
                States.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(100.dp),
                        color = TurtleTheme.color.bottomSheetView
                    )
                }
                is States.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {

                        state.value = SearchNames.filterList(searchState.value.text, sheetModelState.value)

                        if (state.value.pinned.isNotEmpty()) {
                            item(span = { GridItemSpan(4) }) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(bottom = 7.dp),
                                    text = "Закреплённые",
                                    fontFamily = fontGanelas,
                                    fontSize = 18.sp,
                                    color = Color.Gray
                                )
                            }
                            items(state.value.pinned,
                                span = { GridItemSpan(spanSize) }) {
                                Box(
                                    modifier = Modifier
                                        .padding(bottom = 9.dp)
                                        .height(45.dp)
                                        .background(
                                            TurtleTheme.color.transparentBackground,
                                            TurtleTheme.shapes.medium
                                        )
                                        .clip(TurtleTheme.shapes.medium)
                                        .combinedClickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = rememberRipple(),
                                            onClick = { onItemClick(it) },
                                            onLongClick = {
                                                onLongClick(
                                                    state.value,
                                                    it
                                                )
                                            }),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        modifier = Modifier.padding(8.dp),
                                        text = it,
                                        fontFamily = fontGanelas,
                                        color = TurtleTheme.color.titleText,
                                        fontSize = 23.sp
                                    )
                                }
                            }
                        }

                        if (state.value.groups.isNotEmpty()) {
                            item(span = { GridItemSpan(4) }) {
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(bottom = 7.dp),
                                    text = header,
                                    fontFamily = fontGanelas,
                                    fontSize = 18.sp,
                                    color = Color.Gray
                                )
                            }

                            items(state.value.groups,
                                span = { GridItemSpan(spanSize) }) {
                                Box(
                                    modifier = Modifier
                                        .padding(bottom = 9.dp)
                                        .height(45.dp)
                                        .background(
                                            TurtleTheme.color.transparentBackground,
                                            TurtleTheme.shapes.medium
                                        )
                                        .clip(TurtleTheme.shapes.medium)
                                        .combinedClickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = rememberRipple(),
                                            onClick = { onItemClick(it) },
                                            onLongClick = {
                                                onLongClick(
                                                    state.value,
                                                    it
                                                )
                                            }),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    Text(
                                        modifier = Modifier.padding(8.dp),
                                        text = it,
                                        fontFamily = fontGanelas,
                                        color = TurtleTheme.color.titleText,
                                        fontSize = 23.sp
                                    )
                                }
                            }
                        }
                    }
                }
                is States.Error,
                States.NotFoundError -> {
                    ErrorView(
                        Modifier
                            .padding(top = 90.dp)
                            .height(80.dp)
                    ) {
                        onRefreshClick()
                    }
                }
            }
            LaunchedEffect(sheetState.isVisible) {
                if (sheetState.isVisible) {
                    getList()
                }
            }
            LaunchedEffect(sheetState.currentValue) {
                if (sheetState.currentValue != ModalBottomSheetValue.Expanded) {
                    cornersState.value =
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                } else {
                    cornersState.value =
                        RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                }
            }
        }
    }
}