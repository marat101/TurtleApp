package com.turtleteam.ui.screens.scheduleselect

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.*
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
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val groupButtonText = remember {
            mutableStateOf("Группа")
        }
        Column(
            modifier = Modifier
                .offset(y = (-50).dp)
                .background(transparentWhite, RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                painter = painterResource(id = R.drawable.selectgroup),
                contentDescription = null
            )

            Text(
                text = groupButtonText.value,
                style = TextStyle(fontSize = 30.sp, color = Color.Black),
                modifier = Modifier
                    .clickable(onClick = { composableScope.launch { sheetState.show() } })
                    .padding(8.dp)
                    .padding(horizontal = 16.dp)
                    .background(
                        color = transparentWhite,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.done),
                style = TextStyle(fontSize = 30.sp, color = Color.White),
                modifier = Modifier
                    .clickable(onClick = {
                        //Todo open schedule screen
                    })
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
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
            modifier = Modifier.fillMaxWidth(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
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
fun GroupList(
    list: State<NamesList>,
    vModel: ScheduleSelectViewModel,
    onClickAction: (group: String) -> Unit
) {

    val filter = remember { mutableStateOf("") }
    val filteredList = list.value.groups.filter { it.contains(filter.value, true) }
    val filteredPinnedList = list.value.pinned.filter { it.contains(filter.value, true) }
    Column(
        modifier = Modifier
            .background(lightGreen)
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = stringResource(R.string.search),color = Color.Black) },
            maxLines = 1,
            value = filter.value,
            onValueChange = { filter.value = it }
        )


        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            if (filteredPinnedList.isNotEmpty())
                item(span = { GridItemSpan(2) })
                { Text(text = stringResource(R.string.pinned_groups), color = Color.Black) }
            items(filteredPinnedList) {
                BottomSheetOneItem(
                    title = it,
                    onClickAction = { onClickAction(it) },
                    onLongClickAction = { vModel.setPinnedList(it) }
                )
            }
            item(span = { GridItemSpan(2) })
            { Text(text = stringResource(R.string.all_groups),color = Color.Black) }
            items(filteredList) {
                BottomSheetOneItem(
                    title = it,
                    onClickAction = { onClickAction(it) },
                    onLongClickAction = { vModel.setPinnedList(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSheetOneItem(title: String, onClickAction: () -> Unit, onLongClickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .padding(horizontal = 4.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp))
            .combinedClickable(
                onLongClick = onLongClickAction,
                onClick = onClickAction
            ),
//        elevation = 8.dp,

        ) {
        Text(
            modifier = Modifier.background(transparentWhite).padding(4.dp),
            text = title,
            style = TextStyle(fontSize = 25.sp, color = green),
            textAlign = TextAlign.Start
        )
    }
}