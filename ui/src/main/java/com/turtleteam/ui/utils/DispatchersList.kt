package com.turtleteam.ui.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersList {
    fun dispatcherIO(): CoroutineDispatcher
    fun dispatcherDefault(): CoroutineDispatcher
    fun dispatcherMain(): CoroutineDispatcher
    fun dispatcherUnconfined(): CoroutineDispatcher

    class Base : DispatchersList {
        override fun dispatcherIO(): CoroutineDispatcher = Dispatchers.IO
        override fun dispatcherDefault(): CoroutineDispatcher = Dispatchers.Default
        override fun dispatcherMain(): CoroutineDispatcher = Dispatchers.Main
        override fun dispatcherUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
    }
}