package com.turtleteam.turtleappcompose.widget.model

enum class ActionsScheduleWidget(val actionName: String) {
    ACTION_REFRESH(actionName = "com.turtleteam.turtleappcompose.ScheduleWidgetActionRefresh"),
    ACTION_PREVIOUS_DAY(actionName = "com.turtleteam.turtleappcompose.ScheduleWidgetActionPreviousDay"),
    ACTION_NEXT_DAY(actionName = "com.turtleteam.turtleappcompose.ScheduleWidgetActionNextDay")
}