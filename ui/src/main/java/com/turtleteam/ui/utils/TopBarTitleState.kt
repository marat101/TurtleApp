@file:OptIn(ExperimentalFoundationApi::class)

package com.turtleteam.ui.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import org.koin.core.component.KoinComponent

interface TopBarTitleState : PagerListener {

    val topBarTitle: MutableState<String>
}

abstract class TopBarTitleStateImpl : KoinComponent, TopBarTitleState, PagerListenerImpl() {

    override val topBarTitle = mutableStateOf("TurtleApp")
}