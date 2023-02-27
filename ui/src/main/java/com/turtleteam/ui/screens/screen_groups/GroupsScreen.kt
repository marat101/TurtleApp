package com.turtleteam.ui.screens.screen_groups

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.screens.common.components.NamesList
import com.turtleteam.ui.screens.common.components.ScheduleSelectFrame
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.theme.TurtleTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupsScreen(
    viewModel: NamesListViewModel = getViewModel(named("Groups"))
) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val name = remember { mutableStateOf(viewModel.getLastTargetName()) }
    val backgroundShape =
        remember { mutableStateOf(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ScheduleSelectFrame(
            imageId = TurtleTheme.images.selectGroup,
            onOpenList = { scope.launch(Dispatchers.Main) { sheetState.show() } },
            onNextClick = { viewModel.navigateToScheduleScreen(it, false) },
            name = name.value
        )


        ModalBottomSheetLayout(modifier = Modifier.fillMaxWidth(),
            sheetState = sheetState,
            sheetShape = backgroundShape.value,
            content = {},
            sheetContent = {
                NamesList(
                    sheetModelState = state.value,
                    cornersState = backgroundShape,
                    sheetState = sheetState,
                    isTeacher = false,
                    getList = { viewModel.getNamesList() },
                    onItemClick = {
                        viewModel.setLastTargetName(it)
                        name.value = it
                        scope.launch { sheetState.hide() }
                    },
                    onLongClick = { list, item -> viewModel.setPinnedList(list, item) },
                    onRefreshClick = { viewModel.refreshNamesList() })
            })
    }
}