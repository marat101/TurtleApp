package com.turtleteam.widget.widget.utils

import com.turtleteam.widget.R

interface ManageWidgetDrawable {
    fun getBackground(isNightModeOn: Boolean): Int
    fun getRootBackground(isNightModeOn: Boolean): Int
    class Base() : ManageWidgetDrawable {
        override fun getBackground(isNightModeOn: Boolean): Int {
            return if (isNightModeOn) R.drawable.widget_background_night
            else R.drawable.widget_background
        }

        override fun getRootBackground(isNightModeOn: Boolean): Int {
            return if (isNightModeOn) R.drawable.block_corners_night else R.drawable.block_corners
        }
    }
}