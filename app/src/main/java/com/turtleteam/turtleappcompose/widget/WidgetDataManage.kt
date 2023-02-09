package com.turtleteam.turtleappcompose.widget

import android.content.Context
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.turtleappcompose.R

interface WidgetDataManage {
    interface Getters {
        fun getCurrentGroupName(): String
        fun getCurrentDayInt(): Int
        fun getCurrentDayString(): String
        fun getCurrentSchedule(): WidgetScheduleState
        fun getCurrentScheduleString(): String
        fun getCountOfDays(): Int

        class Base(private val context: Context) : Getters {
            private val sharedPreferences =
                context.getSharedPreferences(WIDGET_NAME, Context.MODE_PRIVATE)

            override fun getCurrentGroupName(): String {
                return sharedPreferences.getString(CURRENT_GROUP_NAME, null)
                    ?: context.getString(R.string.error_target_value_is_default)
            }

            override fun getCurrentDayInt(): Int {
                return sharedPreferences.getInt(CURRENT_DAY_INT, 0)
            }

            override fun getCurrentDayString(): String {
                return when (val schedule = getCurrentSchedule()) {
                    is WidgetScheduleState.Success -> schedule.data.days[getCurrentDayInt()].day
                    else -> ""
                }
            }

            override fun getCurrentScheduleString(): String {
                return sharedPreferences.getString(CURRENT_SCHEDULE, null) ?: SCHEDULE_EMPTY_STRING
            }

            override fun getCurrentSchedule(): WidgetScheduleState {
                val scheduleString = getCurrentScheduleString()
                return if (scheduleString == SCHEDULE_EMPTY_STRING) {
                    WidgetScheduleState.Error
                } else
                    WidgetScheduleState.Success(DaysList.toDaysList(scheduleString))

            }

            override fun getCountOfDays(): Int {
                return when (val schedule = getCurrentSchedule()) {
                    is WidgetScheduleState.Success -> schedule.data.days.count()
                    else -> 0
                }
            }
        }
    }

    interface UpdateCurrentDay {
        fun setPreviousDay()
        fun setNextDay()
        class Base(private val context: Context) : UpdateCurrentDay {
            private val editSharedPreference =
                context.getSharedPreferences(WIDGET_NAME, Context.MODE_PRIVATE).edit()
            private val getter = Getters.Base(context)
            override fun setPreviousDay() {
                val currentDay = getter.getCurrentDayInt()
                val count = currentDay - 1
                if (count < 0) return
                editSharedPreference.putInt(CURRENT_DAY_INT, count).apply()
            }

            override fun setNextDay() {
                val currentDay = getter.getCurrentDayInt()
                val count = currentDay + 1
                if (count > (getter.getCountOfDays() - 1)) return
                editSharedPreference.putInt(CURRENT_DAY_INT, count).apply()
            }
        }
    }

    interface SetData {
        fun setSchedule(scheduleJson: String)
        fun setCurrentGroupName(group: String)
        fun setFirstDay()
        class Base(private val context: Context) : SetData {
            private val editSharedPreference =
                context.getSharedPreferences(WIDGET_NAME, Context.MODE_PRIVATE).edit()

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
        const val WIDGET_NAME = "current_schedule"

        const val SCHEDULE_EMPTY_STRING = "current_schedule"
    }
}