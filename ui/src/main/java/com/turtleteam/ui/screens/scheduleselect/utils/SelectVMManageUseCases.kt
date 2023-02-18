package com.turtleteam.ui.screens.scheduleselect.utils

import com.android.turtleapp.data.model.teachersandgroups.NamesList
import com.turtleteam.domain.usecases.usersettings.GetHintStateUseCase
import com.turtleteam.domain.usecases.usersettings.UpdateHintStateUseCase
import com.turtleteam.domain.utils.GetLastTargetUC
import com.turtleteam.domain.utils.GetListAndPinnedListUC
import com.turtleteam.domain.utils.SetLastTargetUC
import com.turtleteam.domain.utils.SetPinnedListUC

sealed class SelectVMManageUseCases(
    private val groupsList: GetListAndPinnedListUC,
    private val getLastTarget: GetLastTargetUC,
    private val setLastTarget: SetLastTargetUC,
    private val setPinnedList: SetPinnedListUC,
    private val updateHintStateUseCase: UpdateHintStateUseCase,
    private val getHintStateUseCase: GetHintStateUseCase
) {
    suspend fun groupsList() = groupsList.execute()
    fun getLastTarget() = getLastTarget.execute()
    fun setLastTarget(name: String) = setLastTarget.execute(name)
    fun setPinnedList(currentList: NamesList, item: String) =
        setPinnedList.execute(currentList, item)
    fun updateTipState(state:Boolean) = updateHintStateUseCase.execute(state)
    fun getTipState(): Boolean = getHintStateUseCase.execute()

    class Groups(
        private val groupsList: GetListAndPinnedListUC,
        private val getLastTargetGroupUseCase: GetLastTargetUC,
        private val setLastTargetGroupUseCase: SetLastTargetUC,
        private val setPinnedList: SetPinnedListUC,
        private val updateHintStateUseCase: UpdateHintStateUseCase,
        private val getHintStateUseCase: GetHintStateUseCase
    ) : SelectVMManageUseCases(
        groupsList,
        getLastTargetGroupUseCase,
        setLastTargetGroupUseCase,
        setPinnedList,
        updateHintStateUseCase,
        getHintStateUseCase
    )

    class Teachers(
        private val teachersList: GetListAndPinnedListUC,
        private val getLastTargetTeacherUseCase: GetLastTargetUC,
        private val setLastTargetTeacherUseCase: SetLastTargetUC,
        private val setPinnedList: SetPinnedListUC,
        private val updateHintStateUseCase: UpdateHintStateUseCase,
        private val getHintStateUseCase: GetHintStateUseCase
    ) : SelectVMManageUseCases(
        teachersList,
        getLastTargetTeacherUseCase,
        setLastTargetTeacherUseCase,
        setPinnedList,
        updateHintStateUseCase,
        getHintStateUseCase
    )
}