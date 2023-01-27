package com.turtleteam.ui.screens.scheduleselect

import com.turtleteam.domain.model.NamesList
import com.turtleteam.domain.usecases.groups.GetGroupsAndPinnedListUseCase
import com.turtleteam.domain.usecases.groups.GetLastTargetGroupUseCase
import com.turtleteam.domain.usecases.groups.SetLastTargetGroupUseCase
import com.turtleteam.domain.usecases.groups.SetPinnedGroupsListUseCase
import com.turtleteam.domain.usecases.teachers.GetLastTargetTeacherUseCase
import com.turtleteam.domain.usecases.teachers.GetTeachersAndPinnedListUseCase
import com.turtleteam.domain.usecases.teachers.SetLastTargetTeacherUseCase
import com.turtleteam.domain.usecases.teachers.SetPinnedTeachersListUseCase
import com.turtleteam.domain.utils.GetLastTargetUC
import com.turtleteam.domain.utils.GetListAndPinnedListUC
import com.turtleteam.domain.utils.SetLastTargetUC
import com.turtleteam.domain.utils.SetPinnedListUC

sealed class SelectVMManageUseCases(
    private val groupsList: GetListAndPinnedListUC,
    private val getLastTarget: GetLastTargetUC,
    private val setLastTarget: SetLastTargetUC,
    private val setPinnedList: SetPinnedListUC,
) {
    suspend fun groupsList() = groupsList.execute()
    fun getLastTarget() = getLastTarget.execute()
    fun setLastTarget(name: String) = setLastTarget.execute(name)
    fun setPinnedList(currentList: NamesList, item: String) =
        setPinnedList.execute(currentList, item)

    class Groups(
        private val groupsList: GetGroupsAndPinnedListUseCase,
        private val getLastTargetGroupUseCase: GetLastTargetGroupUseCase,
        private val setLastTargetGroupUseCase: SetLastTargetGroupUseCase,
        private val setPinnedList: SetPinnedGroupsListUseCase
    ) : SelectVMManageUseCases(
        groupsList,
        getLastTargetGroupUseCase,
        setLastTargetGroupUseCase,
        setPinnedList
    )

    class Teachers(
        private val teachersList: GetTeachersAndPinnedListUseCase,
        private val getLastTargetTeacherUseCase: GetLastTargetTeacherUseCase,
        private val setLastTargetTeacherUseCase: SetLastTargetTeacherUseCase,
        private val setPinnedList: SetPinnedTeachersListUseCase
    ) : SelectVMManageUseCases(
        teachersList,
        getLastTargetTeacherUseCase,
        setLastTargetTeacherUseCase,
        setPinnedList
    )
}