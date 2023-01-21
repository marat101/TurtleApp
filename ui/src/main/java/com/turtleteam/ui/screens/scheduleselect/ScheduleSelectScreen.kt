package com.turtleteam.ui.screens.scheduleselect

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turtleteam.domain.model.NamesList
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.darkGreen
import com.turtleteam.ui.theme.lightGreen
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
            Modifier
                .padding(10.dp)
                .background(Color.Magenta)
        ) {

            Text(
                text = groupButtonText.value,
                style = TextStyle(fontSize = 30.sp, color = Color.Black),
                modifier = Modifier
                    .clickable(onClick = { composableScope.launch { sheetState.show() } })
                    .padding(8.dp)
                    .background(color =Color(255,255,255,0xAA),shape = RoundedCornerShape(16.dp))
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Далее",
                style = TextStyle(fontSize = 30.sp, color = Color.White),
                modifier = Modifier
                    .clickable(onClick = {
                        //Todo open schedule screen
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
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Поиск")},
            maxLines = 1,
            value = filter.value,
            onValueChange = { filter.value = it }
        )


        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            if (filteredPinnedList.isNotEmpty())
                item(span ={ GridItemSpan(2) }) { Text(text = "Закрепленные") }
            items(filteredPinnedList) {
                BottomSheetOneItem(
                    title = it,
                    onClickAction = {onClickAction(it) },
                    onLongClickAction = {vModel.setPinnedList(it) }
                )
            }

            item(span ={ GridItemSpan(2) }){ Text(text = "Все группы") }
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
            .padding(4.dp)
            .combinedClickable(
                onLongClick = onLongClickAction,
                onClick = onClickAction
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