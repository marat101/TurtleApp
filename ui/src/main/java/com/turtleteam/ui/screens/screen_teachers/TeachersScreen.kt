package com.turtleteam.ui.screens.screen_teachers

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeachersScreen(
    page: Int = 0,
    pageListener: PagerListener = get(),
    viewModel: NamesListViewModel = getViewModel(
        named("Teachers"),
    )
) {
    val state = viewModel.state.collectAsState()
    val name = remember { mutableStateOf(viewModel.getLastTargetName()) }
    val scope = rememberCoroutineScope()

    BackHandler(
        pageListener.getPageListener(page)
            .collectAsState(initial = false).value && viewModel.sheetState.isVisible
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
            imageId = TurtleTheme.images.selectTeacher,
            onOpenList = {
                scope.launch(Dispatchers.Main) {
                    viewModel.sheetAlpha.value = 1F
                    viewModel.sheetState.show()
                }
            },
            onNextClick = {
                if (it != "Преподаватели") viewModel.navigateToScheduleScreen(
                    it,
                    true
                ) else viewModel.showSnackbar = true
            },
            name = name.value
        )


        ModalBottomSheetLayout(modifier = Modifier
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
                    isTeacher = true,
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
}