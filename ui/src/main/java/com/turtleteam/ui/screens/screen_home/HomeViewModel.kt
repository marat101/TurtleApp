@file:OptIn(ExperimentalFoundationApi::class)

package com.turtleteam.ui.screens.screen_home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import com.turtleteam.ui.screens.navigation.controller.NavigationController
import com.turtleteam.ui.utils.PagerListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeScreenState(
    val currentPage: Int = 0,
    val bottomBarVisible: Boolean = true
)

abstract class HomeViewModel: ViewModel() {
    abstract val state: StateFlow<HomeScreenState>

    abstract fun navigateToGroups()

    abstract fun navigateToTeachers()

    abstract fun navigateToAdditional()

    abstract fun setPager(pagerState: PagerState)
}

class HomeViewModelImpl(
    private val pagerListener: PagerListener,
): HomeViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    override val state: StateFlow<HomeScreenState>
        get() = _state.asStateFlow()

    override fun navigateToGroups() {
        _state.update { it.copy(currentPage = 0) }
    }

    override fun navigateToTeachers() {
        _state.update { it.copy(currentPage = 1) }
    }

    override fun navigateToAdditional() {
        _state.update { it.copy(currentPage = 2) }
    }

    override fun setPager(pagerState: PagerState) {
        pagerListener.setPagerState(pagerState)
    }
}