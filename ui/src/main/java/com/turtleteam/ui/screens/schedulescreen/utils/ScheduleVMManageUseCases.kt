package com.turtleteam.ui.screens.schedulescreen.utils

import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.usecases.groups.GetGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.GetSavedGroupScheduleUseCase
import com.turtleteam.domain.usecases.groups.SaveGroupScheduleUseCase
import com.turtleteam.domain.usecases.teachers.GetSavedTeacherScheduleUseCase
import com.turtleteam.domain.usecases.teachers.GetTeacherScheduleUseCase
import com.turtleteam.domain.usecases.teachers.SaveTeacherScheduleUseCase
import com.turtleteam.domain.utils.GetSavedScheduleUC
import com.turtleteam.domain.utils.GetScheduleUC
import com.turtleteam.domain.utils.SaveScheduleUC

sealed class ScheduleVMManageUseCases(
    private val getSchedule:GetScheduleUC,
    private val saveSchedule:SaveScheduleUC,
    private val getSavedSchedule:GetSavedScheduleUC
) {
    suspend fun getSchedule(name:String) = getSchedule.execute(name)
    suspend fun saveSchedule(schedule: DaysList) = saveSchedule.execute(schedule)
    suspend fun getSavedSchedule(name:String) = getSavedSchedule.execute(name)

    class Group(
        private val getGroupScheduleUseCase: GetGroupScheduleUseCase,
        private val saveGroupScheduleUseCase: SaveGroupScheduleUseCase,
        private val getSavedGroupScheduleUseCase: GetSavedGroupScheduleUseCase,
    ) : ScheduleVMManageUseCases(
        getGroupScheduleUseCase,
        saveGroupScheduleUseCase,
        getSavedGroupScheduleUseCase
    )

    class Teacher(
        private val getTeachersScheduleUseCase: GetTeacherScheduleUseCase,
        private val saveTeacherScheduleUseCase: SaveTeacherScheduleUseCase,
        private val getSavedTeacherScheduleUseCase: GetSavedTeacherScheduleUseCase
    ) : ScheduleVMManageUseCases(getTeachersScheduleUseCase,
            saveTeacherScheduleUseCase,
            getSavedTeacherScheduleUseCase) {

    }
}
