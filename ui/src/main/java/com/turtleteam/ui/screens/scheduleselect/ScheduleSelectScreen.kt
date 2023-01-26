package com.turtleteam.ui.screens.scheduleselect

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.utils.SearchNames
import com.turtleteam.ui.R
import com.turtleteam.ui.screens.navigation.Routes
import com.turtleteam.ui.theme.darkGreen
import com.turtleteam.ui.theme.green
import com.turtleteam.ui.theme.lightGreen
import com.turtleteam.ui.theme.transparentWhite
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
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val groupButtonText = viewModel.currentGroup.collectAsState()
        Column(
            Modifier
                .offset(y = (-50).dp)
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.6f),
                painter = painterResource(id = R.drawable.selectgroup),
                contentDescription = null
            )
            Text(
                text = groupButtonText.value,
                style = TextStyle(fontSize = 30.sp, color = Color.Black),
                modifier = Modifier
                    .clickable(onClick = { composableScope.launch { sheetState.show() } })
                    .padding(8.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.done),
                style = TextStyle(fontSize = 30.sp, color = Color.White),
                modifier = Modifier
                    .clickable(onClick = {
                        navController.navigate(Routes.SCHEDULE_SCREEN.route + "/${groupButtonText.value}")
                    })
                    .padding(8.dp)
                    .background(
                        Brush.horizontalGradient(colors = listOf(darkGreen, lightGreen)),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }
        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 20f, topEnd = 20f),
            content = {},
            sheetContent = {
                GroupList(viewModel)
            }
        )
    }
}


@Composable
fun GroupList(
    viewModel: ScheduleSelectViewModel
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
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.search), color = Color.Black) },
            maxLines = 1,
            singleLine = true,
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
                    content = { NamesHeader(stringResource(id = R.string.pinned_groups)) })
                items(filteredList.value.pinned) {
                    NameItem(viewModel = viewModel, title = it)
                }
            }
            if (filteredList.value.groups.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = { NamesHeader(stringResource(id = R.string.all_groups)) })
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
                onClick = { viewModel.setGroup(title) },
            ),
        elevation = 8.dp,
    ) {
        Text(
            modifier = Modifier
                .background(transparentWhite)
                .padding(4.dp),
            text = title,
            style = TextStyle(fontSize = 25.sp, color = green),
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