package com.turtleteam.ui.screens.scheduleselect

import android.content.Context
import com.turtleteam.ui.R

class ValidateGroupAndTeacher(private val string: String) {
    fun isCurrentValueDefault(context: Context, isTeachers: Boolean): Boolean {
        val defaultValue = context.getString(
            if (isTeachers) R.string.teachers
            else R.string.groups
        )
        return (defaultValue == string)
    }
}