package com.turtleteam.data.repository

import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.ktor_client.api.ApiService
import com.turtleteam.turtle_database.dao.TeachersScheduleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TeachersRepositoryImpl(
    private val teachersScheduleDao: TeachersScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore
) : ScheduleRepository {

    override suspend fun getSchedule(name: String): DaysList = apiService.getSchedule(name)

    override fun getSavedSchedule(name: String): Flow<DaysList?> {
        return teachersScheduleDao.getTeacherDaysList(name).map {
            if (it != null)
                DaysList(Json.decodeFromString(it.days), it.name)
            else
                null
        }
    }

    override suspend fun saveSchedule(schedule: DaysList) =
        teachersScheduleDao.saveTeacherDaysList(Json.encodeToString(schedule.days), schedule.name)

    override fun getSavedName(): String? =
        preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID2)

    override fun saveName(string: String) =
        preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID2, string)

    override suspend fun getNamesList(): List<String> = apiService.getList().teacher

    override suspend fun getSavedNamesList(): List<String>? =
        teachersScheduleDao.getSavedScheduleList()

    override fun getPinnedList(): List<String> =
        runCatching { preferencesStore.getPinnedList(PreferencesStore.PINNED_TEACHERS) }.getOrDefault(
            emptyList()
        )

    override fun savePinnedList(list: List<String>) =
        preferencesStore.savePinnedList(PreferencesStore.PINNED_TEACHERS, list)


    override fun getLastTargetName(): String {
        return preferencesStore.getLastTargetTeacher()
    }

    override fun setLastTargetName(name: String) {
        preferencesStore.setLastTargetTeacher(name)
    }
}