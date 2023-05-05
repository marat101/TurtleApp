@file:OptIn(ExperimentalFoundationApi::class)

package com.turtleteam.ui.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow


interface PagerListener: PagerUserScroll {
    fun getPageListener(page: Int): Flow<Boolean>

    fun setPagerState(pagerState: PagerState)
}

interface PagerUserScroll {
    val isUserScrollEnabled: MutableState<Boolean>
}

abstract class PagerListenerImpl : TopBarTitleState, PagerListener, PagerUserScroll {

    override val isUserScrollEnabled = mutableStateOf(true)
    var pager: PagerState? = null

    override fun setPagerState(pagerState: PagerState) {
        pager = pagerState
    }

    override fun getPageListener(page: Int): Flow<Boolean> {
        return snapshotFlow {
            pager?.currentPage == page
        }
    }
}