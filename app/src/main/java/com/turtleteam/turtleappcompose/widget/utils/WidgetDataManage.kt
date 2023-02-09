package com.turtleteam.turtleappcompose.widget.utils

import android.content.Context
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.turtleappcompose.R
import com.turtleteam.turtleappcompose.widget.model.WidgetScheduleState
import com.turtleteam.turtleappcompose.widget.model.WidgetSuccessStateData

interface WidgetDataManage {
    interface Getters {
        fun getCurrentGroupName(): String
        fun getCurrentDayInt(): Int
        fun getCurrentDayString(): String
        fun getCurrentSchedule(): WidgetScheduleState
        fun getCurrentScheduleString(): String
        fun getCountOfDays(): Int
        fun getDaysCount(): String

        class Base(private val context: Context) : Getters {
            private val sharedPreferences =
                context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

            override fun getCurrentGroupName(): String {
                return sharedPreferences.getString(CURRENT_GROUP_NAME, null)
                    ?: context.getString(R.string.error_target_value_is_default)
            }

            override fun getCurrentDayInt(): Int {
                return sharedPreferences.getInt(CURRENT_DAY_INT, 0)
            }

            override fun getCurrentDayString(): String {
                return when (val schedule = getCurrentSchedule()) {
                    is WidgetScheduleState.Success ->schedule.getCurrentDay(getCurrentDayInt())
                    else -> ""
                }
            }

            override fun getCurrentScheduleString(): String {
                return sharedPreferences.getString(CURRENT_SCHEDULE, null) ?: SCHEDULE_EMPTY_STRING
            }

            override fun getCurrentSchedule(): WidgetScheduleState {
                val scheduleString = getCurrentScheduleString()
                return if (scheduleString == SCHEDULE_EMPTY_STRING)
                    WidgetScheduleState.ErrorScheduleNotSelected
                else
                    WidgetScheduleState.Success(
                        WidgetSuccessStateData.fromDaysList(
                            DaysList.toDaysList(
                                scheduleString
                            )
                        ),
                        context
                    )

            }

            override fun getCountOfDays(): Int {
                return when (val schedule = getCurrentSchedule()) {
                    is WidgetScheduleState.Success -> schedule.getCountDays()
                    else -> 0
                }
            }

            override fun getDaysCount(): String {
                return "${getCurrentDayInt()+1}/${getCountOfDays()}"
            }
        }
    }

    interface UpdateCurrentDay {
        fun setPreviousDay():Boolean
        fun setNextDay():Boolean
        class Base(private val context: Context) : UpdateCurrentDay {
            private val editSharedPreference =
                context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
            private val getter = Getters.Base(context)
            override fun setPreviousDay(): Boolean {
                val currentDay = getter.getCurrentDayInt()
                val count = currentDay - 1
                return if (count < 0) false
                else {
                    editSharedPreference.putInt(CURRENT_DAY_INT, count).apply()
                    true
                }
            }

            override fun setNextDay(): Boolean {
                val currentDay = getter.getCurrentDayInt()
                val count = currentDay + 1
                return if (count > (getter.getCountOfDays() - 1)) false
                else {
                    editSharedPreference.putInt(CURRENT_DAY_INT, count).apply()
                    true
                }
            }
        }
    }

    interface SetData {
        fun setSchedule(scheduleJson: String)
        fun setCurrentGroupName(group: String)
        fun setFirstDay()
        class Base(private val context: Context) : SetData {
            private val editSharedPreference =
                context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()

            override fun setSchedule(scheduleJson: String) {
                editSharedPreference.putString(CURRENT_SCHEDULE, scheduleJson).apply()
            }

            override fun setCurrentGroupName(group: String) {
                editSharedPreference.putString(CURRENT_GROUP_NAME, group).apply()
            }

            override fun setFirstDay() {
                editSharedPreference.putInt(CURRENT_DAY_INT, 0).apply()
            }
        }
    }


    companion object {
        const val CURRENT_GROUP_NAME = "current_group_name"
        const val CURRENT_DAY_INT = "current_day_int"
        const val CURRENT_SCHEDULE = "current_schedule"
        const val SHARED_PREFERENCES_NAME = "shared_preferences_name"

        const val SCHEDULE_EMPTY_STRING = "schedule_empty_string"
    }
}