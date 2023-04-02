@file:OptIn(ExperimentalFoundationApi::class)

package com.turtleteam.ui.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.Flow


interface PagerListener {
    fun getPageListener(page: Int): Flow<Boolean>

    fun setPagerState(pagerState: PagerState)
}

abstract class PagerListenerImpl : TopBarTitleState, PagerListener {

    var pager:PagerState? = null

    override fun setPagerState(pagerState: PagerState) {
        pager = pagerState
    }

    override fun getPageListener(page: Int): Flow<Boolean> {
        return snapshotFlow {
            pager?.currentPage == page
        }
    }
}