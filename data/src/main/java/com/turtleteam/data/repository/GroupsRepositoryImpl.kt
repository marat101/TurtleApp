package com.turtleteam.data.repository

import com.android.turtleapp.data.local.entity.GroupsDaysList
import com.android.turtleapp.data.local.wrapper.LocalResultWrapper
import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.data.api.ApiService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.data.wrapper.NetworkResultWrapper
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.turtle_database.database.GroupsScheduleDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GroupsRepositoryImpl(
    private val groupsScheduleDao: GroupsScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore,
) : GroupsRepository {

    override suspend fun getSchedule(group: String): States<DaysList> =
        NetworkResultWrapper.wrapWithResult { apiService.getGroupsScheduleList(group) }

    override suspend fun getSavedSchedule(group: String): States<DaysList> =
        LocalResultWrapper().wrapWithResult {
            val value = groupsScheduleDao.getGroupDaysList(group)
            GroupsDaysList(Json.decodeFromString(value.days), value.name)
        }

    override suspend fun saveSchedule(schedule: DaysList) =
        groupsScheduleDao.saveGroupDaysList(Json.encodeToString(schedule.days), schedule.name)

    override fun getSavedName(): String? =
        preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID)

    override fun saveName(string: String) =
        preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID, string)

    override suspend fun getGroupsList(): List<String> =
        runCatching { apiService.getGroupsList().group }.getOrDefault(groupsScheduleDao.getSavedScheduleList())

    override fun getPinnedList(): List<String> =
        runCatching { preferencesStore.getPinnedList(PreferencesStore.PINNED_GROUPS) }.getOrDefault(
            emptyList()
        )

    override fun savePinnedList(list: List<String>) =
        preferencesStore.savePinnedList(PreferencesStore.PINNED_GROUPS, list)

    override fun getLastTargetGroup(): String {
        return preferencesStore.getLastTargetGroup()
    }

    override fun setLastTargetGroup(group: String) {
        preferencesStore.setLastTargetGroup(group)
    }
}