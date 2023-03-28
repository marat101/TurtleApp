@file:OptIn(ExperimentalFoundationApi::class)

package com.turtleteam.ui.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.*
import com.turtleteam.domain.usecases_impl.usersettings.GetThemeStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.SaveThemeStateUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface MainScreenStates : PagerListener {

    val bottomBarVisible: MutableState<Boolean>

    val topBarTitle: MutableState<String>

    val pagerState: PagerState

    val isDarkMode: MutableState<Boolean>

    fun setTheme(dark: Boolean)
}

abstract class MainScreenStatesImpl : KoinComponent, MainScreenStates, PagerListenerImpl() {

    private val getThemeStateUseCase: GetThemeStateUseCase by inject()
    private val saveThemeStateUseCase: SaveThemeStateUseCase by inject()

    override val bottomBarVisible = mutableStateOf(false)

    override val topBarTitle = mutableStateOf("TurtleApp")

    override val pagerState = PagerState()

    override val isDarkMode = mutableStateOf(getThemeStateUseCase.execute())

    override fun setTheme(dark: Boolean) {
        saveThemeStateUseCase.execute(dark)
        bottomBarVisible.value = !bottomBarVisible.value
    }
}