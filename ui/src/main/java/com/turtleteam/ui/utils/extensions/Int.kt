package com.turtleteam.ui.utils.extensions

fun Int.toDayOfWeek(): String {
    return when (this) {
        1 -> "ПН"
        2 -> "ВТ"
        3 -> "СР"
        4 -> "ЧТ"
        5 -> "ПТ"
        6 -> "СБ"
        7 -> "ВС"
        else -> {
            "ВС"
        }
    }
}