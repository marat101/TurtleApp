package com.turtleteam.ui.utils

import kotlinx.coroutines.flow.StateFlow

interface Communication<T> {
    fun map(value:T)
    fun observe():StateFlow<T>
}