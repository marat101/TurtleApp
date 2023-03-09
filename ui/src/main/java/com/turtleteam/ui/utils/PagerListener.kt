package com.turtleteam.ui.utils

import androidx.compose.runtime.snapshotFlow
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.flow.Flow


interface PagerListener: BackPress {
    suspend fun getPageListener(page: Int): Flow<Boolean>
}

abstract class PagerListenerImpl : MainScreenStates, PagerListener, BackPressImpl() {
    @OptIn(ExperimentalPagerApi::class)
    override suspend fun getPageListener(page: Int): Flow<Boolean> {
        return snapshotFlow {
            pagerState.currentPage == page
        }
    }
}