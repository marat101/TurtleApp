package com.turtleteam.data.repository

import com.android.turtleapp.data.repository.interfaces.TeachersRepository
import com.turtleteam.data.api.ApiService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.data.wrapper.LocalResultWrapper
import com.turtleteam.data.wrapper.NetworkResultWrapper
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.turtle_database.database.TeachersScheduleDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TeachersRepositoryImpl(
    private val teachersScheduleDao: TeachersScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore
) : TeachersRepository {

    override suspend fun getSchedule(teacher: String): States<DaysList> =
        NetworkResultWrapper.wrapWithResult { apiService.getTeachersSchedulelist(teacher) }

    override suspend fun getSavedSchedule(teacher: String): States<DaysList> =
        LocalResultWrapper().wrapWithResult {
            val value = teachersScheduleDao.getTeacherDaysList(teacher)
            DaysList(Json.decodeFromString(value.days), value.name)
        }

    override suspend fun saveSchedule(schedule: DaysList) =
        teachersScheduleDao.saveTeacherDaysList(Json.encodeToString(schedule.days), schedule.name)

    override fun getSavedName(): String? =
        preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID2)

    override fun saveName(string: String) =
        preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID2, string)

    override suspend fun getTeachersList(): List<String> =
        runCatching { apiService.getGroupsList().teacher }.getOrDefault(teachersScheduleDao.getSavedScheduleList())

    override fun getPinnedList(): List<String> =
        runCatching { preferencesStore.getPinnedList(PreferencesStore.PINNED_TEACHERS) }.getOrDefault(
            emptyList()
        )

    override fun savePinnedList(list: List<String>) =
        preferencesStore.savePinnedList(PreferencesStore.PINNED_TEACHERS, list)
}