package com.turtleteam.data.repository

import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.ktor_client.api.ApiService
import com.turtleteam.turtle_database.dao.GroupsScheduleDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GroupsRepositoryImpl(
    private val groupsScheduleDao: GroupsScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore,
) : ScheduleRepository {

    override suspend fun getSchedule(name: String): DaysList = apiService.getSchedule(name)

    override fun getSavedSchedule(name: String): Flow<DaysList?> {
        return groupsScheduleDao.getGroupDaysList(name).map {
            if (it != null)
                DaysList(Json.decodeFromString(it.days), it.name)
            else
                null
        }
    }

    override suspend fun saveSchedule(schedule: DaysList) =
        groupsScheduleDao.saveGroupDaysList(Json.encodeToString(schedule.days), schedule.name)

    override fun getSavedName(): String? =
        preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID)

    override fun saveName(string: String) =
        preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID, string)

    override suspend fun getNamesList(): List<String> = apiService.getList().group

    override suspend fun getSavedNamesList(): List<String>? =
        groupsScheduleDao.getSavedScheduleList()

    override fun getPinnedList(): List<String> =
        runCatching { preferencesStore.getPinnedList(PreferencesStore.PINNED_GROUPS) }.getOrDefault(
            emptyList()
        )

    override fun savePinnedList(list: List<String>) =
        preferencesStore.savePinnedList(PreferencesStore.PINNED_GROUPS, list)

    override fun getLastTargetName(): String {
        return preferencesStore.getLastTargetGroup()
    }

    override fun setLastTargetName(name: String) {
        preferencesStore.setLastTargetGroup(name)
    }
}