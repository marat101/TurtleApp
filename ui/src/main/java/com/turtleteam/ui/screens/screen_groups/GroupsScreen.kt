package com.turtleteam.ui.screens.screen_groups

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.turtleteam.ui.screens.common.components.NamesList
import com.turtleteam.ui.screens.common.components.ScheduleSelectFrame
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.screens.common.views.Snackbar
import com.turtleteam.ui.theme.TurtleTheme
import com.turtleteam.ui.utils.PagerListener
import com.turtleteam.ui.utils.PagerUserScroll
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
    viewModel: NamesListViewModel = getViewModel(
        named("Groups"),
        parameters = { parametersOf(page) })
) {
    val state = viewModel.state.collectAsState()
    val name = remember { mutableStateOf(viewModel.getLastTargetName()) }
    val scope = rememberCoroutineScope()
    val isCurrentPage = pageListener.getPageListener(page).collectAsState(initial = false)

    BackHandler(
        isCurrentPage.value && viewModel.sheetState.isVisible
    ) {
        scope.launch {
            viewModel.sheetState.hide()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ScheduleSelectFrame(
            imageId = TurtleTheme.images.selectGroup,
            onOpenList = {
                viewModel.sheetAlpha.value = 1F
                scope.launch {
                    viewModel.sheetState.show()
                }
            },
            onNextClick = {
                if (it != "Группы") viewModel.navigateToScheduleScreen(
                    it,
                    false
                ) else viewModel.showSnackbar = true
            },
            name = name.value
        )


        ModalBottomSheetLayout(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(viewModel.sheetAlpha.value),
            sheetState = viewModel.sheetState,
            sheetShape = viewModel.backgroundShape.value,
            scrimColor = Color(0xA6000000),
            content = {},
            sheetContent = {
                NamesList(
                    listState = state.value,
                    cornersState = viewModel.backgroundShape,
                    sheetState = viewModel.sheetState,
                    isTeacher = false,
                    getList = { viewModel.getNamesList() },
                    onItemClick = {
                        viewModel.setLastTargetName(it)
                        name.value = it
                        scope.launch { viewModel.sheetState.hide() }
                    },
                    onLongClick = { list, item -> viewModel.setPinnedList(list, item) },
                    onHideHint = { viewModel.setHintBoxVisibility() },
                    hint = viewModel.getHintBoxVisibility()
                )
            })
        LaunchedEffect(viewModel.sheetState.isVisible) {
            if (!viewModel.sheetState.isVisible) viewModel.sheetAlpha.value = 0F
        }
        if (viewModel.showSnackbar)
            Snackbar(
                modifier = Modifier.align(Alignment.BottomCenter),
                message = "Выберите расписание!",
                showSb = viewModel.showSnackbar
            ) {
                viewModel.showSnackbar = it
            }
    }
    LaunchedEffect(key1 = isCurrentPage.value, key2 = viewModel.sheetState.isVisible,block = {
        if(!isCurrentPage.value && viewModel.sheetState.isVisible)
            viewModel.sheetState.hide()
        pageListener.isUserScrollEnabled.value =
            !(isCurrentPage.value && viewModel.sheetState.isVisible)
    })
}