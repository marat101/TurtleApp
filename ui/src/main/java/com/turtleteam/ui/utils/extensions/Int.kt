package com.turtleteam.ui.utils.extensions

fun Int.toDayOfWeek(): String {
    return when (this) {
        2 -> "ПН"
        3 -> "ВТ"
        4 -> "СР"
        5 -> "ЧТ"
        6 -> "ПТ"
        7 -> "СБ"
        1 -> "ВС"
        else -> {
            "ВС"
        }
    }
}