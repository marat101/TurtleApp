package com.turtleteam.ui.utils

import androidx.activity.OnBackPressedCallback
import com.turtleteam.ui.screens.navigation.controller.Navigator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


interface BackPress {
    fun setAction(action: () -> Boolean)

    val callBack: OnBackPressedCallback
}

class BackPressImpl : BackPress, KoinComponent {
    private val navigator: Navigator by inject()

    override var callBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

        }
    }

    override fun setAction(action: () -> Boolean) {
        val mAction = action()
        if (!mAction)
            navigator.navigateBack()
    }
}