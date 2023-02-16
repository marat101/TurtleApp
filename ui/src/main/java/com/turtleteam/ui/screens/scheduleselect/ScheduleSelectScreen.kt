package com.turtleteam.ui.screens.scheduleselect

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turtleteam.domain.utils.SearchNames
import com.turtleteam.ui.R
import com.turtleteam.ui.screens.navigation.Routes
import com.turtleteam.ui.theme.*
import com.turtleteam.ui.utils.TextWithFont
import com.turtleteam.ui.utils.TiledButton
import com.turtleteam.ui.utils.views.GradientButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleSelectScreen(
    navController: NavHostController,
    isTeacher: Boolean,
    viewModel: ScheduleSelectViewModel,
) {
    val composableScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val groupButtonText = viewModel.getTargetGroupFlow().collectAsState()
        Column(
            Modifier
                .offset(y = (-50).dp)
                .background(JetTheme.color.transparentBackground, RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(top = 16.dp),
                painter = painterResource(
                    if (isTeacher) JetTheme.images.selectTeacher
                    else JetTheme.images.selectGroup
                ),
                contentDescription = null
            )
            TiledButton(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {
                    viewModel.updateGroupsList()
                    composableScope.launch { sheetState.show() }
                },
                backgroundDrawableId = JetTheme.images.btnSelect,
            ) {
                TextWithFont(
                    text = groupButtonText.value,
                    color = JetTheme.color.btnGroupTeacherText,
                    textSize = 24.sp
                )
            }
            GradientButton(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .padding(bottom = 16.dp)
                    .height(50.dp),
                onClick = {
                    navController.navigate(Routes.SCHEDULE_SCREEN.route + "/${groupButtonText.value}/$isTeacher")
                },
                gradient = JetTheme.color.toolbarGradient,
                indicationColor = JetTheme.color.secondText
            ) {
                TextWithFont(
                    text = stringResource(id = R.string.done),
                    color = JetTheme.color.btnDoneText,
                    textSize = 24.sp
                )
            }
        }
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            content = {},
            sheetContent = {
                GroupList(viewModel, sheetState, isTeacher)
            }
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupList(
    viewModel: ScheduleSelectViewModel,
    sheetState: ModalBottomSheetState,
    isTeacher: Boolean,
) {
    val groupsList = viewModel.getGroupsListFlow().collectAsState()
    val query = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val filteredList = SearchNames.filterList(query.value, groupsList.value)
    val isTipVisible = remember { mutableStateOf(viewModel.getHintState()) }
    Column(
        modifier = Modifier
            .background(JetTheme.color.backgroundBrush)
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.search),
                    color = JetTheme.color.simpleText
                )
            },
            maxLines = 1,
            singleLine = true,
            value = query.value,
            onValueChange = {
                query.value = it
            }
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(if (isTeacher) 1 else 2)
        ) {
            if (isTipVisible.value) {
                item(
                    span = { GridItemSpan(2) },
                    content = {
                        HintBox {
                            viewModel.notShowHint()
                            isTipVisible.value = false
                        }
                    })
            }
            if (filteredList.pinned.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = { NamesHeader(stringResource(id = R.string.pinned_groups)) })
                items(filteredList.pinned) {
                    NameItem(
                        viewModel = viewModel,
                        title = it,
                        sheetState = sheetState,
                        coroutineScope = coroutineScope
                    )
                }
            }
            if (filteredList.groups.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = {
                        NamesHeader(
                            stringResource(if (isTeacher) R.string.all_teachers else R.string.all_groups)
                        )
                    }
                )
                items(filteredList.groups) {
                    NameItem(
                        viewModel = viewModel,
                        title = it,
                        sheetState = sheetState,
                        coroutineScope = coroutineScope
                    )
                }
            }
        }
    }
}

@Composable
fun HintBox(onCloseClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(JetTheme.color.secondText, RoundedCornerShape(8.dp))
            .padding(2.dp)
            .background(JetTheme.color.backgroundBrush, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(16.dp)
                .offset(y = (-4).dp, x = 4.dp)
                .clickable { onCloseClick() },
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.ic_add_favorites),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.schedule_pin_hint),
                color = JetTheme.color.simpleText
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun NameItem(
    title: String, viewModel: ScheduleSelectViewModel,
    sheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(
                onLongClick = {
                    viewModel.pinOrUnpinItem(title)
                },
                onClick = {
                    viewModel.setTargetGroup(title)
                    coroutineScope.launch { sheetState.hide() }
                },
            ),
        backgroundColor = JetTheme.color.bottomDialogBackItemColor,
        elevation = 8.dp
    ) {
        TextWithFont(
            modifier = Modifier
                .padding(8.dp), text = title, color = JetTheme.color.titleText, textSize = 25.sp
        )
    }
}

@Composable
fun NamesHeader(title: String) {
    Box(
        Modifier.padding(4.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(text = title, color = JetTheme.color.simpleText, fontFamily = fontFamily)
    }
}