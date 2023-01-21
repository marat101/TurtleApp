package com.turtleteam.ui.screens.scheduleselect

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.utils.SearchNames
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleSelectScreen(
    navController: NavHostController,
    viewModel: ScheduleSelectViewModel = koinViewModel()
) {

    viewModel.getGroupsList()
    val composableScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val groupButtonText = remember {
            mutableStateOf("Группа")
        }
        Column(
            Modifier
                .padding(10.dp)
                .background(Color.Magenta)
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(8.dp),
                onClick = { viewModel.getGroupsList()
                    composableScope.launch { sheetState.show() } },
                shape = RoundedCornerShape(16.dp),
                colors = buttonColors(backgroundColor = Color.Green)
            ) { Text(text = groupButtonText.value, style = TextStyle(fontSize = 30.sp)) }

            Button(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(8.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    //Todo open schedule screen
                },
                colors = buttonColors(backgroundColor = Color.Green)

            ) { Text(text = "Далее", style = TextStyle(fontSize = 30.sp)) }

        }
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20f, topEnd = 20f),
            content = {},
            sheetContent = {
                GroupList(viewModel) {
                    groupButtonText.value = it
                    composableScope.launch { sheetState.hide() }
                }
            }
        )
    }
}


@Composable
fun GroupList(
    viewModel: ScheduleSelectViewModel,
    onClickAction: (group: String) -> Unit
) {

    val baseList = viewModel.groups.collectAsState()


    val query = remember {
        mutableStateOf("")
    }

    val filteredList = remember { mutableStateOf(NamesList(emptyList(), emptyList())) }
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
    ) {
        TextField(
            maxLines = 1,
            value = query.value,
            onValueChange = {
                query.value = it
                filteredList.value = SearchNames.filterList(query.value, baseList.value)
            }
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.fillMaxSize()) {
            if (filteredList.value.pinned.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = { NamesHeader("Закрепленные") })
                items(filteredList.value.pinned) {
                    NameItem(viewModel = viewModel, title = it)
                }
            }
            if (filteredList.value.groups.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = { NamesHeader("Все группы") })
                items(filteredList.value.groups) {
                    NameItem(viewModel = viewModel, title = it)
                }
            }
        }
    }
    LaunchedEffect(key1 = null) {
        viewModel.groups.collectLatest {
            filteredList.value = SearchNames.filterList(query.value, baseList.value)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NameItem(title: String, viewModel: ScheduleSelectViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(
                onLongClick = { viewModel.setPinnedList(title) },
                onClick = {},
            ),
        elevation = 8.dp,
    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 25.sp),
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun NamesHeader(title: String) {
    Box(
        Modifier
            .padding(1.dp)
            .background(
                Color.DarkGray,
                RoundedCornerShape(
                    CornerSize(4.dp),
                    CornerSize(4.dp),
                    CornerSize(4.dp),
                    CornerSize(4.dp)
                )
            )
            .fillMaxSize(),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(Modifier.padding(8.dp)) {
            Text(text = title, color = Color.White)
        }
    }
}