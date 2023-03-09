package com.turtleteam.ui.utils


interface BackPress {
    fun setAction(action: () -> Unit)
}

abstract class BackPressImpl: BackPress {
    override fun setAction(action: () -> Unit) {

    }
}