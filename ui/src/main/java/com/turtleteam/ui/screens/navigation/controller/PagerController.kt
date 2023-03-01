package com.turtleteam.ui.screens.navigation.controller

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

interface PagerController {

    @OptIn(ExperimentalPagerApi::class)
    fun setPagerState(pagerState: PagerState)

    fun setPagerScroll(enabled: Boolean = true)

    fun gerPagerScroll():
}