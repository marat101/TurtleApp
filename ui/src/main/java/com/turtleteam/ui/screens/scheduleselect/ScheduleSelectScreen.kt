package com.turtleteam.ui.screens.scheduleselect

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ScheduleSelectScreen(
    navController: NavHostController,
    viewModel: ScheduleSelectViewModel = koinViewModel()
) {

    val groups = viewModel.groups.collectAsState()

    Column(
        Modifier
            .padding(3.dp)
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Button(
            onClick = { viewModel.getGroupsList() },
        ) {
            Text(text = "Получить список групп")
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            if (groups.value.pinned.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = { NamesHeader("Закрепленные") })
                items(groups.value.pinned) {
                    NameItem(viewModel = viewModel, item = it)
                }
            }
            if (groups.value.groups.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = { NamesHeader("Все группы") })
                items(groups.value.groups) {
                    NameItem(viewModel = viewModel, item = it)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NameItem(viewModel: ScheduleSelectViewModel, item: String) {
    Box(
        Modifier
            .padding(1.dp)
            .background(
                Color.Gray,
                RoundedCornerShape(
                    CornerSize(4.dp),
                    CornerSize(4.dp),
                    CornerSize(4.dp),
                    CornerSize(4.dp)
                )
            )
            .fillMaxSize()
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    viewModel.setPinnedList(item)
                },
            )
    ) {
        Text(text = item, color = Color.White)
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