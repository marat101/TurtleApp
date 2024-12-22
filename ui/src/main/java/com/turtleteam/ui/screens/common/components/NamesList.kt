package com.turtleteam.ui.screens.common.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turtleteam.domain.model.other.StatefulModel
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.utils.SearchNames
import ru.turtleteam.theme.R
import com.turtleteam.ui.screens.common.views.CustomTextField
import ru.turtleteam.theme.TurtleTheme
import ru.turtleteam.theme.fontQanelas

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NamesList(
    listState: StatefulModel<NamesList>,
    cornersState: MutableState<RoundedCornerShape>,
    sheetState: ModalBottomSheetState,
    getList: () -> Unit,
    onItemClick: (name: String) -> Unit,
    onLongClick: (list: NamesList, item: String) -> Unit,
    onHideHint: () -> Unit,
    hint: Boolean,
    isTeacher: Boolean
) {

    val spanSize = if (isTeacher) 4 else 2
    val header = if (isTeacher) "Все преподаватели" else "Все группы"
    val state = remember { mutableStateOf(NamesList.empty) }
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
        Row {
            CustomTextField(
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 3.dp, vertical = 3.dp),
                placeholder = "Поиск…",
                value = searchState.value,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                onValueChange = { searchState.value = it })
            if (searchState.value.isNotEmpty())
                IconButton(
                    modifier = Modifier.height(28.dp),
                    onClick = { searchState.value = "" }) {
                    Icon(
                        modifier = Modifier.height(20.dp),
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        tint = TurtleTheme.color.secondText
                    )
                }
        }
        if (hintVisibility.value)
            HintBox() {
                onHideHint()
                hintVisibility.value = false
            }




        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            listState.data?.let { names ->
                state.value = SearchNames.filterList(searchState.value, names)

                if (state.value.pinned.isNotEmpty()) {
                    item(span = { GridItemSpan(4) }) {
                        Text(
                            modifier = Modifier
                                .padding(bottom = 7.dp),
                            text = "Закреплённые",
                            fontFamily = fontQanelas,
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
                                    names,
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
                                .padding(bottom = 7.dp),
                            text = header,
                            fontFamily = fontQanelas,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }

                    items(state.value.groups, span = { GridItemSpan(spanSize) }) {
                        NameItem(
                            onItemClick = { onItemClick(it) },
                            onLongClick = {
                                onLongClick(
                                    names,
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
            item(span = { GridItemSpan(4) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    when (listState.loadingState) {
                        States.Error -> {
                            ErrorView(
                                Modifier
                                    .padding(top = 40.dp)
                                    .height(80.dp)
                            ) {
                                getList()
                            }
                        }
                        States.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(vertical = 40.dp)
                                    .size(35.dp),
                                color = TurtleTheme.color.bottomSheetView
                            )
                        }
                        else -> {}
                    }
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NameItem(
    onItemClick: () -> Unit,
    onLongClick: () -> Unit,
    item: String
) {
    Box(
        modifier = Modifier
            .padding(bottom = 7.dp)
            .height(45.dp)
            .shadow(5.dp, TurtleTheme.shapes.medium)
            .background(
                TurtleTheme.color.nameItemBackground,
                TurtleTheme.shapes.medium
            )
            .clip(TurtleTheme.shapes.medium)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
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
            fontFamily = fontQanelas,
            color = TurtleTheme.color.titleText,
            fontSize = 23.sp
        )
    }
}
