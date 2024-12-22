package com.turtleteam.ui.screens.screen_teachers

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import com.turtleteam.ui.screens.common.components.NamesList
import com.turtleteam.ui.screens.common.components.ScheduleSelectFrame
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.screens.common.views.Snackbar
import ru.turtleteam.theme.TurtleTheme
import com.turtleteam.ui.utils.PagerListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
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
    val isCurrentPage = pageListener.getPageListener(page).collectAsState(initial = false)
    val density = LocalDensity.current
    val sheetState = remember { ModalBottomSheetState(ModalBottomSheetValue.Hidden, density) }

    BackHandler(
        isCurrentPage.value && sheetState.isVisible
    ) {
        scope.launch {
            sheetState.hide()
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
                    sheetState.show()
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
            sheetState = sheetState,
            sheetShape = viewModel.backgroundShape.value,
            scrimColor = Color(0xA6000000),
            content = {},
            sheetContent = {
                NamesList(
                    listState = state.value,
                    cornersState = viewModel.backgroundShape,
                    sheetState = sheetState,
                    isTeacher = true,
                    getList = { viewModel.getNamesList() },
                    onItemClick = {
                        viewModel.setLastTargetName(it)
                        name.value = it
                        scope.launch { sheetState.hide() }
                    },
                    onLongClick = { list, item -> viewModel.setPinnedList(list, item) },
                    onHideHint = { viewModel.setHintBoxVisibility() },
                    hint = viewModel.getHintBoxVisibility()
                )
            })
        LaunchedEffect(sheetState.isVisible) {
            if (!sheetState.isVisible) viewModel.sheetAlpha.value = 0F
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
    LaunchedEffect(key1 = isCurrentPage.value, key2 = sheetState.isVisible,block = {
        if(!isCurrentPage.value && sheetState.isVisible)
            sheetState.hide()
        pageListener.isUserScrollEnabled.value =
            !(isCurrentPage.value && sheetState.isVisible)
    })
}