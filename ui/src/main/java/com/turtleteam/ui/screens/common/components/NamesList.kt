package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
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
    isTeacher: Boolean
) {

    val state = remember { mutableStateOf(NamesList.empty) }
    val spanSize = if (isTeacher) 4 else 2
    val header = if (isTeacher) "Все преподаватели" else "Все группы"

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
                .fillMaxWidth()
                .height(40.dp),
            value = TextFieldValue(),
            onValueChange = {},
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            when (sheetModelState) {
                is States.Error,
                States.NotFoundError -> TODO()
                States.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(100.dp),
                        color = TurtleTheme.color.bottomSheetView
                    )
                }
                is States.Success -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        verticalArrangement = Arrangement.spacedBy(9.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {

                        state.value = sheetModelState.value

                        if (state.value.pinned.isNotEmpty()) {
                            item(span = { GridItemSpan(4) }) {
                                Text(
                                    modifier = Modifier.align(Alignment.BottomStart),
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
                                        .height(45.dp)
                                        .background(
                                            TurtleTheme.color.transparentBackground,
                                            TurtleTheme.shapes.medium
                                        )
                                        .combinedClickable(
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
                                    modifier = Modifier.align(Alignment.BottomStart),
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
                                        .height(45.dp)
                                        .background(
                                            TurtleTheme.color.transparentBackground,
                                            TurtleTheme.shapes.medium
                                        )
                                        .combinedClickable(
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
            }
            LaunchedEffect(sheetState.isVisible) {
                if (sheetState.isVisible) {
                    getList()
                }
            }
            LaunchedEffect(sheetState.currentValue) {
                if (sheetState.currentValue != ModalBottomSheetValue.Expanded) {
                    cornersState.value = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                } else {
                    cornersState.value = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                }
            }
        }
    }
}