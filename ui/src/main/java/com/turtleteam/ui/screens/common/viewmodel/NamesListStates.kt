package com.turtleteam.ui.screens.common.viewmodel

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.turtleteam.ui.screens.common.viewmodel.base.BaseViewModel

@OptIn(ExperimentalMaterialApi::class)
abstract class NamesListStates : BaseViewModel() {
    val sheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var showSnackbar by mutableStateOf(false)
    val backgroundShape = mutableStateOf(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    val sheetAlpha = mutableStateOf(1F)
}