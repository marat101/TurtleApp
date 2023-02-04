package com.turtleteam.ui.utils

import androidx.compose.runtime.MutableState

fun MutableState<Boolean>.switch():Boolean {
    this.value = !this.value
    return this.value
}