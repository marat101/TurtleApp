package com.turtleteam.ui.screens.screen_groups

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.turtleteam.ui.screens.common.components.NamesList
import com.turtleteam.ui.screens.common.components.ScheduleSelectFrame
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.screens.common.views.Snackbar
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.utils.PagerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GroupsScreen(
    page: Int = 0,
    pageListener: PagerListener = get(),
    viewModel: NamesListViewModel = getViewModel(named("Groups"), parameters = { parametersOf(page) })
) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val name = remember { mutableStateOf(viewModel.getLastTargetName()) }
    val backgroundShape =
        remember { mutableStateOf(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) }
    var showSnackbar by remember { mutableStateOf(false) }
    val sheetAlpha = remember { mutableStateOf(1F) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ScheduleSelectFrame(
            imageId = TurtleTheme.images.selectGroup,
            onOpenList = {
                scope.launch(Dispatchers.Main) {
                    sheetAlpha.value = 1F
                    viewModel.sheetState.show()
                }
            },
            onNextClick = {
                if (it != "Группы") viewModel.navigateToScheduleScreen(
                    it,
                    false
                ) else showSnackbar = true
            },
            name = name.value
        )


        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(sheetAlpha.value),
            sheetState = viewModel.sheetState,
            sheetShape = backgroundShape.value,
            scrimColor = Color(0xA6000000),
            content = {},
            sheetContent = {
                NamesList(
                    listState = state.value,
                    cornersState = backgroundShape,
                    sheetState = viewModel.sheetState,
                    isTeacher = false,
                    getList = { viewModel.getNamesList() },
                    onItemClick = {
                        viewModel.setLastTargetName(it)
                        name.value = it
                        scope.launch { viewModel.sheetState.hide() }
                    },
                    onLongClick = { list, item -> viewModel.setPinnedList(list, item) },
                    onRefreshClick = { viewModel.refreshNamesList() },
                    onHideHint = { viewModel.setHintBoxVisibility() },
                    hint = viewModel.getHintBoxVisibility()
                )
            })
        LaunchedEffect(viewModel.sheetState.isVisible) {
            if (!viewModel.sheetState.isVisible) sheetAlpha.value = 0F
        }
        if (showSnackbar)
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                message = "Выберите расписание!",
                showSb = showSnackbar
            ) {
                showSnackbar = it
            }
    }
}