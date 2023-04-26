package com.turtleteam.widget_schedule.utils

import com.turtleteam.widget_schedule.schedule_select.SelectType

fun String.toSelectType(): SelectType = if (this == SelectType.GROUP.name) SelectType.GROUP else SelectType.TEACHER

fun SelectType.isGroup(): Boolean = this.name == SelectType.GROUP.name

fun SelectType.isTeacher(): Boolean = this.name == SelectType.TEACHER.name