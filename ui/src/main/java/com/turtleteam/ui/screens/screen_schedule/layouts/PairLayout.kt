package com.turtleteam.ui.screens.screen_schedule.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.ui.screens.screen_schedule.components.PairItem

@Composable
fun PairLayout(day: Day) {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(items = day.apairs) { pair ->
                Column(Modifier.padding(top = 14.dp)) {
                    PairItem(pairs = pair)
                }
            }
        }
    }
}
