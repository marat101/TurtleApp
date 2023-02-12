package com.turtleteam.widget.widget.utils

import com.turtleteam.widget.R

interface ManageWidgetColor {
    fun getTitleTextColor(isNightModeOn: Boolean): Int
    fun getDayCountTextColor(isNightModeOn: Boolean): Int
    fun getSecondTextColor(isNightModeOn: Boolean): Int

    class Base() : ManageWidgetColor {
        override fun getTitleTextColor(isNightModeOn: Boolean): Int {
            return if (isNightModeOn) R.color.widgetTextTitleColor_NIGHT
            else R.color.widgetTextTitleColor_DAY
        }

        override fun getDayCountTextColor(isNightModeOn: Boolean): Int {
            return R.color.widgetTextDayCount
        }

        override fun getSecondTextColor(isNightModeOn: Boolean): Int {
            return if (isNightModeOn) R.color.widgetTextSecondColor_NIGHT
            else R.color.widgetTextSecondColor_DAY
        }
    }
}