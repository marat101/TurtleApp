package com.turtleteam.ui.screens.common.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

abstract class NamesListStates : ViewModel() {
    @OptIn(ExperimentalMaterialApi::class)
    val sheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
}