package com.turtleteam.data.repository

import com.android.turtleapp.data.local.dao.TeachersScheduleDao
import com.android.turtleapp.data.local.entity.TeachersDaysList
import com.android.turtleapp.data.local.wrapper.LocalResultWrapper
import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.data.api.ApiService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.data.wrapper.NetworkResultWrapper
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList

class TeachersRepositoryImpl(
    private val teachersScheduleDao: TeachersScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore
) : TeachersRepository {

    override suspend fun getSchedule(teacher: String): States<DaysList> =
        NetworkResultWrapper.wrapWithResult { apiService.getTeachersSchedulelist(teacher) }

    override suspend fun getSavedSchedule(teacher: String): States<DaysList> =
        LocalResultWrapper().wrapWithResult { teachersScheduleDao.getScheduleByName(teacher) }

    override suspend fun saveSchedule(schedule: DaysList) = teachersScheduleDao.insert(
        TeachersDaysList(schedule.days, schedule.name)
    )

    override fun getSavedName(): String? =
        preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID2)

    override fun saveName(string: String) =
        preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID2, string)

    override suspend fun getTeachersList(): List<String> =
        runCatching { apiService.getGroupsList().teacher }.getOrDefault(teachersScheduleDao.getScheduleList())

    override fun getPinnedList(): List<String> =
        runCatching { preferencesStore.getPinnedList(PreferencesStore.PINNED_TEACHERS) }.getOrDefault(
            emptyList()
        )

    override fun savePinnedList(list: List<String>) =
        preferencesStore.savePinnedList(PreferencesStore.PINNED_TEACHERS, list)
}