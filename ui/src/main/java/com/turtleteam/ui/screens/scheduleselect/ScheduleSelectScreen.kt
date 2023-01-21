package com.turtleteam.ui.screens.scheduleselect

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleSelectScreen(
    navController: NavHostController,
    viewModel: ScheduleSelectViewModel = koinViewModel()
) {

    val groups = viewModel.groups.collectAsState()
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
                onClick = { composableScope.launch { sheetState.show() } },
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
                .fillMaxWidth(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20f, topEnd = 20f),
            content = {},
            sheetContent = {
                GroupList(groups, viewModel) {
                    groupButtonText.value = it
                    composableScope.launch { sheetState.hide() }
                }
            }
        )
    }
}



@Composable
fun GroupList(list:State<NamesList>, vModel: ScheduleSelectViewModel , onClickAction: (group: String) -> Unit) {

    val filter = remember { mutableStateOf("") }
    val filteredList = list.value.groups.filter { it.contains(filter.value, true) }
    val filteredPinnedList = list.value.pinned.filter { it.contains(filter.value, true) }
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
    ) {
        TextField(
            maxLines = 1,
            value = filter.value,
            onValueChange = { filter.value = it }
        )


        Text(text = "Закрепленные")
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(filteredPinnedList.size) {
                val item = filteredPinnedList[it]
                BottomSheetOneItem(title = item, onClickAction = {
                    Log.d("xdd", "onClickAction ")

                    onClickAction(item)
                },
                    onLongClickAction = {
                        Log.d("xdd", "onLongClickAction ")

                        vModel.setPinnedList(item)
                    }
                )
            }
        }
        Text(text = "Все группы")
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(filteredList.size) {
                val item = filteredList[it]
                BottomSheetOneItem(title = item, onClickAction = {
                    Log.d("xdd", "onClickAction ")
                    onClickAction(item)
                },
                onLongClickAction = {
                    Log.d("xdd", "onLongClickAction ")

                    vModel.setPinnedList(item)
                }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun BottomSheetOneItem(title: String, onClickAction: () -> Unit, onLongClickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(
                onLongClick = onLongClickAction,
                onClick = onClickAction,
            ),
        elevation = 8.dp,
        onClick = onClickAction,

    ) {
        Text(
            text = title,
            style = TextStyle(fontSize = 25.sp),
            textAlign = TextAlign.Start
        )
    }
}