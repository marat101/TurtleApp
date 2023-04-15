package com.turtleteam.ui.utils.extensions

import android.icu.text.SimpleDateFormat
import java.util.*

fun String.toDate() = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT).parse(this)!!

fun Date.toDayOfMonth(): String = SimpleDateFormat.getPatternInstance("d MMMM", Locale.forLanguageTag("RU")).format(this)