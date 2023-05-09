package com.turtleteam.ui.utils.extensions

import android.icu.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.toDate() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.forLanguageTag("RU")).parse(this)!!

fun Date.toDayOfMonth(): String =
    SimpleDateFormat.getPatternInstance("d MMMM", Locale.forLanguageTag("RU")).format(this)

fun String.toCalendar(): Calendar {
    val timeInMillis = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.forLanguageTag("RU")).parse(this).time
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timeInMillis
    return calendar
}

fun Calendar.toMonth(): String = SimpleDateFormat("d MMMM", Locale.forLanguageTag("RU")).format(time)
