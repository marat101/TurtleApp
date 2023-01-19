package com.turtleteam.data.repository

import com.android.turtleapp.data.local.dao.GroupsScheduleDao
import com.android.turtleapp.data.local.entity.GroupsDaysList
import com.android.turtleapp.data.local.wrapper.LocalResultWrapper
import com.android.turtleapp.data.repository.interfaces.GroupsRepository
import com.turtleteam.data.api.ApiService
import com.turtleteam.data.preferences.PreferencesStore
import com.turtleteam.data.wrapper.NetworkResultWrapper
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList

class GroupsRepositoryImpl (
    private val groupsScheduleDao: GroupsScheduleDao,
    private val apiService: ApiService,
    private val preferencesStore: PreferencesStore,
): GroupsRepository {

    override suspend fun getSchedule(group: String): States<DaysList> = NetworkResultWrapper.wrapWithResult { apiService.getGroupsScheduleList(group) }

    override suspend fun getSavedSchedule(group: String): States<DaysList> = LocalResultWrapper().wrapWithResult { groupsScheduleDao.getScheduleByName(group) }

    override suspend fun saveSchedule(schedule: DaysList) = groupsScheduleDao.insert(GroupsDaysList(schedule.days, schedule.name))

    override fun getSavedName(): String? = preferencesStore.getSavedItem(PreferencesStore.SELECTED_ID)

    override fun saveName(string: String) = preferencesStore.saveSelectedItem(PreferencesStore.SELECTED_ID, string)

    override suspend fun getGroupsList(): List<String> = runCatching { apiService.getGroupsList().group }.getOrDefault(groupsScheduleDao.getScheduleList())

    override fun getPinnedList(): List<String> = runCatching { preferencesStore.getPinnedList(PreferencesStore.PINNED_GROUPS) }.getOrDefault(emptyList())

    override fun savePinnedList(list: List<String>) = preferencesStore.savePinnedList(PreferencesStore.PINNED_GROUPS, list)
}