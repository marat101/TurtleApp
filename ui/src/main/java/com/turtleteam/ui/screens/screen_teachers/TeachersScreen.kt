package com.turtleteam.ui.screens.screen_teachers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.turtleteam.ui.screens.common.components.ScheduleSelectFrame
import com.turtleteam.ui.screens.common.viewmodel.NamesListViewModel
import com.turtleteam.ui.theme.TurtleTheme
import org.koin.androidx.compose.getViewModel
import org.koin.core.qualifier.named

@Composable
fun TeachersScreen(
    viewModelWrapper: NamesListViewModel = getViewModel(named("Teachers"))
){
    val state = viewModelWrapper.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
        ScheduleSelectFrame(
            imageId = TurtleTheme.images.selectTeacher,
            onOpenList = { /*TODO*/ },
            onNextClick = { /*TODO*/ },
            name = "aaaaaa"
        )
        Text(text = state.value.groups.toString())
    }
    LaunchedEffect(key1 = null){
        viewModelWrapper.getNamesList()
    }
}