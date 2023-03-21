package com.turtleteam.ui.screens.common.viewmodel.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(): ViewModel() {

    suspend fun handleResult(
        execute: suspend () -> Unit,
        onFailure: (suspend (exception: Throwable) -> Unit)? = null
    ){
        try {
            execute()
        } catch (e: Throwable){
            onFailure?.invoke(e)
        }
    }
}