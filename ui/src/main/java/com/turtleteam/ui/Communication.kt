package com.turtleteam.ui

import kotlinx.coroutines.flow.StateFlow

interface Communication<T> {
    fun map(value:T)
    fun observe():StateFlow<T>
}