package com.turtleteam.ui.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow


interface PagerListener {
    fun getPageListener(page: Int): Flow<Boolean>
}

abstract class PagerListenerImpl : MainScreenStates, PagerListener {
    @OptIn(ExperimentalFoundationApi::class)
    override fun getPageListener(page: Int): Flow<Boolean> {
        return snapshotFlow {
            pagerState.currentPage == page
        }
    }
}