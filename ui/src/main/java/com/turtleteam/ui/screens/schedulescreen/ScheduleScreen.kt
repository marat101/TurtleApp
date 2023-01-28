package com.turtleteam.ui.screens.schedulescreen

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.android.turtleapp.data.model.schedule.Day
import com.android.turtleapp.data.model.schedule.Pair
import com.android.turtleapp.data.model.schedule.PairsList
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ui.R

@Composable
fun ScheduleScreen(
    navController: NavHostController,
    nameGroupOfTeacher: String,
    vModel: ScheduleScreenViewModel<ScheduleVMManageUseCases>
) {
    vModel.updateSchedule(nameGroupOfTeacher)
    val scheduleState: State<States<DaysList>> = vModel.getFlow().collectAsState()
    Log.d("xdd", "${scheduleState.value.hashCode()}")
    Box(Modifier.fillMaxSize()){
        when (scheduleState.value) {
            is States.Success -> {
                ShowSchedule(daysList = (scheduleState.value as States.Success<DaysList>).value)
            }
            else -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ShowSchedule(daysList: DaysList) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        daysList.days.forEach {
            OneDay(it)
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun OneDay(it: Day) {
    Column(
        Modifier
            .padding(start = 46.dp)
            .padding(end = 46.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(text = it.day)
        Apairs(it.apairs)
    }
}

@Composable
fun Apairs(apairs: List<PairsList>) {
    apairs.forEach { it ->
        Text(text = stringResource(id = R.string.index_number_apair, it.apair[0].number))
        it.apair.forEach {
            OneApair(it)
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun OneApair(it: Pair) {
    TextWithIcon(R.drawable.hourglass, it.start + " - " + it.end, 5.dp)
    TextWithIcon(drawableId = R.drawable.book, text = it.doctrine, 5.dp)
    TextWithIcon(drawableId = R.drawable.school_hat, text = it.teacher, 5.dp)
    TextWithIcon(drawableId = R.drawable.door, text = it.auditoria, 5.dp)
    TextWithIcon(drawableId = R.drawable.corpus, text = it.corpus)
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
        Text(text = text)
    }
    Spacer(modifier = Modifier.height(spacerHeight))
}
