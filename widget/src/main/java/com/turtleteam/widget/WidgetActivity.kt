package com.turtleteam.widget

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.turtleteam.domain.usecases.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.utils.SearchNames
import com.turtleteam.widget.theme.JetTheme
import com.turtleteam.widget.theme.TurtleAppTheme
import com.turtleteam.widget.theme.fontFamily
import com.turtleteam.widget.widget.updateScheduleWidget
import com.turtleteam.widget.widget.utils.InflateWidget
import com.turtleteam.widget.widget.utils.NotifyWidget
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.named

class WidgetActivity : ComponentActivity() {
    private val us:GetThemeStateUseCase  by inject()
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wId = intent.extras?.getInt("id") ?: throw IllegalArgumentException("require widget id")
        setContent {
            TurtleAppTheme(darkTheme = us.execute()) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HorizontalPager(
                        count = 2,
                        state = rememberPagerState()
                    ) { page ->
                        when (page) {
                            0 -> GroupList(
                                viewModel = koinViewModel(named("WidgetActivityGroups")),
                                isTeacher = false,
                                wId
                            )
                            1 -> GroupList(
                                viewModel = koinViewModel(named("WidgetActivityTeachers")),
                                isTeacher = true,
                                wId
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GroupList(
    viewModel: WidgetViewModel,
    isTeacher: Boolean,
    wId: Int,
) {
    val groupsList = viewModel.namesFlow.collectAsState()
    val query = remember { mutableStateOf("") }
    val filteredList = SearchNames.filterList(query.value, groupsList.value)
    val isHintVisible = remember { mutableStateOf(viewModel.getHintState()) }
    val activity = (LocalContext.current as? Activity)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(JetTheme.color.backgroundBrush)
            .padding(horizontal = 8.dp)
            .padding(top = 8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(
                    text = stringResource(R.string.search),
                    color = JetTheme.color.simpleText
                )
            },
            maxLines = 1,
            singleLine = true,
            value = query.value,
            onValueChange = {
                query.value = it
            }
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(if (isTeacher) 1 else 2)
        ) {
            if (isHintVisible.value) {
                item(
                    span = { GridItemSpan(2) },
                    content = {
                        HintBox {
                            viewModel.notShowHint()
                            isHintVisible.value = false
                        }
                    })
            }
            if (filteredList.pinned.isNotEmpty()) {
                item(span = { GridItemSpan(4) }) {
                    NamesHeader(stringResource(id = R.string.pinned_groups))
                }
                items(filteredList.pinned) {
                    NameItem(
                        title = it,
                        onLongClick = { viewModel.pinOrUnpinItem(it) },
                        onClick = {
                            scope.launch {
                                viewModel.setNewSchedule(it)
                                updateScheduleWidget(
                                    InflateWidget.Base(R.layout.schedule_widget,context,wId),
                                    NotifyWidget.Base(AppWidgetManager.getInstance(context),wId, context)
                                )
                                activity?.finish()
                            }
                        }
                    )
                }
            }
            if (filteredList.groups.isNotEmpty()) {
                item(
                    span = { GridItemSpan(4) },
                    content = {
                        NamesHeader(
                            stringResource(if (isTeacher) R.string.all_teachers else R.string.all_groups)
                        )
                    }
                )
                items(filteredList.groups) {
                    NameItem(
                        title = it,
                        onLongClick = { viewModel.pinOrUnpinItem(it) },
                        onClick = {
                            scope.launch {
                                viewModel.setNewSchedule(it)
                                updateScheduleWidget(
                                    InflateWidget.Base(R.layout.schedule_widget,context,wId),
                                    NotifyWidget.Base(AppWidgetManager.getInstance(context),wId, context)
                                )
                                activity?.finish()
                            }
                        }
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = groupsList) {
        viewModel.updateList()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NameItem(
    title: String,
    onLongClick: () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .combinedClickable(onLongClick = onLongClick, onClick = onClick),
        backgroundColor = JetTheme.color.bottomDialogBackItemColor,
        elevation = 8.dp
    ) {
        TextWithFont(
            modifier = Modifier
                .padding(8.dp), text = title, color = JetTheme.color.titleText, textSize = 25.sp
        )
    }
}

@Composable
fun NamesHeader(title: String) {
    Box(
        Modifier.padding(4.dp), contentAlignment = Alignment.CenterStart
    ) {
        Text(text = title, color = JetTheme.color.simpleText, fontFamily = fontFamily)
    }
}

@Composable
fun HintBox(onCloseClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(JetTheme.color.secondText, RoundedCornerShape(8.dp))
            .padding(2.dp)
            .background(JetTheme.color.backgroundBrush, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(16.dp)
                .offset(y = (-4).dp, x = 4.dp)
                .clickable { onCloseClick() },
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = null
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.ic_add_favorites),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.schedule_pin_hint),
                color = JetTheme.color.simpleText
            )
        }
    }
}

@Composable
fun TextWithFont(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    textSize: TextUnit,
    align: TextAlign = TextAlign.Start,
) {
    Text(
        modifier = modifier,
        text = text,
        fontFamily = fontFamily,
        color = color,
        fontSize = textSize,
        textAlign = align
    )
}