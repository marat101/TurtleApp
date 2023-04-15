package com.turtleteam.ui.screens.screen_schedule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.turtleapp.data.model.schedule.Day
import com.turtleteam.ui.R
import com.turtleteam.ui.theme.LocalColors
import com.turtleteam.ui.theme.fontQanelas
import com.turtleteam.ui.utils.extensions.toDate
import com.turtleteam.ui.utils.extensions.toDayOfMonth
import com.turtleteam.ui.utils.extensions.toDayOfWeek

@Composable
fun DateItem(day: Day) {
    val date = remember { day.isoDateDay.toDate() }
    Row(
        modifier = Modifier
            .padding(top = 5.dp, bottom = 2.dp)
            .height(31.dp)
            .shadow(elevation = 4.dp, RoundedCornerShape(12.dp), clip = false)
            .background(LocalColors.current.dateBackground, RoundedCornerShape(12.dp))
            .padding(horizontal = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left), contentDescription = "",
            tint = LocalColors.current.textColor
        )
        Text(
            text = "${date.day.toDayOfWeek()} ${date.toDayOfMonth()}",
            fontSize = 20.sp,
            fontWeight = FontWeight(700),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = LocalColors.current.textColor
        )
        Icon(
            modifier = Modifier.rotate(180F),
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            tint = LocalColors.current.textColor
        )
    }
}
