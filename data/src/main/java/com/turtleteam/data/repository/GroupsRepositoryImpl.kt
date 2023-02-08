package com.turtleteam.data.repository

import com.android.turtleapp.data.local.entity.GroupsDaysList
import com.turtleteam.ktor_client.api.ApiService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.data.wrapper.LocalResultWrapper
import com.turtleteam.data.wrapper.NetworkResultWrapper
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.domain.repository.ScheduleRepository
import com.turtleteam.turtle_database.dao.GroupsScheduleDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GroupsRepositoryImpl(
    private val groupsScheduleDao: GroupsScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore,
) : ScheduleRepository {

    override suspend fun getSchedule(name: String): States<DaysList> =
        NetworkResultWrapper.wrapWithResult { apiService.getSchedule(name) }

    override suspend fun getSavedSchedule(name: String): States<DaysList> =
        LocalResultWrapper().wrapWithResult {
            val value = groupsScheduleDao.getGroupDaysList(name)
            GroupsDaysList(Json.decodeFromString(value.days), value.name)
        }

    override suspend fun saveSchedule(schedule: DaysList) =
        groupsScheduleDao.saveGroupDaysList(Json.encodeToString(schedule.days), schedule.name)

    override fun getSavedName(): String? =
        preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID)

    override fun saveName(string: String) =
        preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID, string)

    override suspend fun getNamesList(): List<String> =
        runCatching { apiService.getList().group }.getOrDefault(groupsScheduleDao.getSavedScheduleList())

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