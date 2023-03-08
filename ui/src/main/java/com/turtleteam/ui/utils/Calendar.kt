package com.turtleteam.ui.utils

import java.text.SimpleDateFormat
import java.util.*


class Calendar {
    fun changeDay(): String {
        val day = when (getCurrentDay()[0]) {
            "01" -> "1"
            "02" -> "2"
            "03" -> "3"
            "04" -> "4"
            "05" -> "5"
            "06" -> "6"
            "07" -> "7"
            "08" -> "8"
            "09" -> "9"
            else -> {
                getCurrentDay()[0]
            }
        }
        return day
    }
}

fun getCurrentDay(): List<String> {
    val day = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val dayFormat = day.format(Date())
    return dayFormat.split(".")
}
