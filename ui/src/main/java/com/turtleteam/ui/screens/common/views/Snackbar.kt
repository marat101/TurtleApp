package com.turtleteam.ui.screens.common.views

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Snackbar(
    modifier: Modifier = Modifier,
    message: String,
    showSb: Boolean,
    openSnackbar: (Boolean) -> Unit
) {

    val snackState = remember { SnackbarHostState() }

    SnackbarHost(
        modifier = modifier,
        hostState = snackState
    ) {
        Snackbar(
            snackbarData = it,
            contentColor = Color.White
        )
    }


    if (showSb) {
        LaunchedEffect(Unit) {
            snackState.showSnackbar(message)
            delay(1000L)
            openSnackbar(false)
        }
    }
}