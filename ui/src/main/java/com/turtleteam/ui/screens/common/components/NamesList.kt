package com.turtleteam.ui.screens.common.components

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.utils.SearchNames
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.theme.fontGanelas
import com.turtleteam.ui.utils.views.BaseTextField

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun NamesList(
    listState: States<NamesList>,
    cornersState: MutableState<RoundedCornerShape>,
    sheetState: ModalBottomSheetState,
    getList: () -> Unit,
    onItemClick: (name: String) -> Unit,
    onLongClick: (list: NamesList, item: String) -> Unit,
    onRefreshClick: () -> Unit,
    onHideHint: () -> Unit,
    hint: Boolean,
    isTeacher: Boolean
) {

    val state = remember { mutableStateOf(NamesList.empty) }
    val spanSize = if (isTeacher) 4 else 2
    val header = if (isTeacher) "Все преподаватели" else "Все группы"
    val searchState = remember { mutableStateOf("") }
    val hintVisibility = remember { mutableStateOf(hint) }
    val focusManager = LocalFocusManager.current

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
//        TextField(
//            modifier = Modifier
//                .fillMaxWidth(),
//            value = TextFieldValue(),
//            onValueChange = {
//                searchState.value
//            },
//            singleLine = true,
//            colors = TextFieldDefaults.apply {
//                TextFieldDecorationBox(
//                    value = searchState.value,
//                    innerTextField = {},
//                    enabled = false,
//                    singleLine = true,
//                    visualTransformation = VisualTransformation.None,
//                    interactionSource = MutableInteractionSource(),
//                    contentPadding = PaddingValues(0.dp)
//                )
//                textFieldColors(
//                    textColor = TurtleTheme.color.secondText,
//                    cursorColor = Color.Gray,
//                    backgroundColor = Color.Transparent,
//                    focusedIndicatorColor = Color.Gray
//                )
//            },
//            textStyle = TextStyle(
//                fontFamily = fontGanelas,
//                fontSize = 25.sp
//            ),
//            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
//        )
        BaseTextField(
            modifier = Modifier.padding(horizontal = 3.dp, vertical = 3.dp),
            placeholder = "Поиск",
            value = searchState.value,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            onValueChange = { searchState.value = it })

        if (hintVisibility.value)
            HintBox()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {

            when (listState) {
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

                        state.value =
                            SearchNames.filterList(searchState.value, listState.value)

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
                                NameItem(
                                    onItemClick = { onItemClick(it) },
                                    onLongClick = {
                                        onLongClick(
                                            listState.value,
                                            it
                                        )
                                        if (hintVisibility.value) {
                                            hintVisibility.value = false
                                            onHideHint()
                                        }
                                    },
                                    item = it
                                )
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
                                NameItem(
                                    onItemClick = { onItemClick(it) },
                                    onLongClick = {
                                        onLongClick(
                                            listState.value,
                                            it
                                        )
                                        if (hintVisibility.value) {
                                            hintVisibility.value = false
                                            onHideHint()
                                        }
                                    },
                                    item = it
                                )
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
                } else {
                    searchState.value = ""
                    focusManager.clearFocus()
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NameItem(
    onItemClick: () -> Unit,
    onLongClick: () -> Unit,
    item: String
) {
    Box(
        modifier = Modifier
            .padding(bottom = 9.dp)
            .height(45.dp)
            .shadow(5.dp, TurtleTheme.shapes.medium)
            .background(
                TurtleTheme.color.nameItemBackground,
                TurtleTheme.shapes.medium
            )
            .clip(TurtleTheme.shapes.medium)
            .combinedClickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                onClick = {
                    onItemClick()
                },
                onLongClick = {
                    onLongClick()
                }),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = item,
            fontFamily = fontGanelas,
            color = TurtleTheme.color.titleText,
            fontSize = 23.sp
        )
    }
}