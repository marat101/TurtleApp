package com.turtleteam.ui.screens.schedulescreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair
import com.android.turtleapp.data.model.schedule.PairsList
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.R
import com.turtleteam.ui.TextWithFont
import com.turtleteam.ui.theme.JetTheme

@Composable
fun ScheduleScreen(
    navController: NavHostController,
    nameGroupOfTeacher: String,
    vModel: ScheduleScreenViewModel<ScheduleVMManageUseCases>
) {
    val scheduleState: State<States<DaysList>> = vModel.getFlow().collectAsState()
    Box(Modifier.fillMaxSize()) {
        when (scheduleState.value) {
            is States.Success -> {
                ShowSchedule(daysList = (scheduleState.value as States.Success<DaysList>).value)
            }
            else -> {
                CircularProgressIndicator()
            }
        }
    }
    LaunchedEffect(key1 = scheduleState) {
        vModel.updateSchedule(nameGroupOfTeacher)
    }
}

@Composable
fun ShowSchedule(daysList: DaysList) {
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        items(daysList.days) {
            OneDay(it)
        }
    }
}

@Composable
fun OneDay(it: Day) {
    Column(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(JetTheme.color.transparentBackground, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
            TextWithFont(text = it.day, color = JetTheme.color.titleText, textSize = 25.sp)
        }
        Apairs(it.apairs)
    }
}

@Composable
fun Apairs(apairs: List<PairsList>) {
    apairs.forEach { it ->
        TextWithFont(text = stringResource(id = R.string.index_number_apair, it.apair[0].number), color = JetTheme.color.titleText, textSize = 25.sp)
//        Text(text = stringResource(id = R.string.index_number_apair, it.apair[0].number), Modifier.padding(start = 5.dp))
        it.apair.forEach {
            OneApair(it)
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun OneApair(it: Pair) {
    Column(Modifier.padding(5.dp)) {
        TextWithIcon(R.drawable.hourglass, it.start + " - " + it.end, 5.dp)
        TextWithIcon(drawableId = R.drawable.book, text = it.doctrine, 5.dp)
        TextWithIcon(drawableId = R.drawable.school_hat, text = it.teacher, 5.dp)
        TextWithIcon(drawableId = R.drawable.door, text = it.auditoria, 5.dp)
        TextWithIcon(drawableId = R.drawable.corpus, text = it.corpus)
    }
}

@Composable
fun TextWithIcon(@DrawableRes drawableId: Int, text: String, spacerHeight: Dp = 0.dp) {
    if (text.isBlank()) return
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(2.dp))
        TextWithFont(text = text,color = JetTheme.color.secondText, textSize = 18.sp)
    }
    Spacer(modifier = Modifier.height(spacerHeight))
}