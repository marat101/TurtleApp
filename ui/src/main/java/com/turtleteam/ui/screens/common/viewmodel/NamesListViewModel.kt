package com.turtleteam.ui.screens.common.viewmodel

import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.other.StatefulModel
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.usecases.*
import com.turtleteam.domain.usecases_impl.usersettings.GetHintStateUseCase
import com.turtleteam.domain.usecases_impl.usersettings.UpdateHintStateUseCase
import com.turtleteam.ui.screens.navigation.controller.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class NamesListViewModel : NamesListStates() {

    abstract val state: StateFlow<StatefulModel<NamesList>>

    abstract fun setLastTargetName(name: String)

    abstract fun getNamesList()

    abstract fun setPinnedList(list: NamesList, item: String)

    abstract fun navigateToScheduleScreen(name: String, isTeacher: Boolean)

    abstract fun getLastTargetName(): String

    abstract fun setHintBoxVisibility()

    abstract fun getHintBoxVisibility(): Boolean
}

data class NamesListUsecasesProvider(
    val getLastTargetUC: GetLastTargetUC,
    val setLastTargetUC: SetLastTargetUC,
    val setPinnedListUC: SetPinnedListUC,
    val getPinnedListUC: GetPinnedListUC,
    val getNamesList: GetNamesListUC,
    val getSavedNamesList: GetSavedNamesListUC,
    val setHintStateUC: UpdateHintStateUseCase,
    val getHintStateUC: GetHintStateUseCase
)

class NamesViewModelImpl(
    private val navigator: Navigator,
    private val usecase: NamesListUsecasesProvider
) : NamesListViewModel() {

    private val _state = MutableStateFlow<StatefulModel<NamesList>>(StatefulModel())
    override val state: StateFlow<StatefulModel<NamesList>>
        get() = _state.asStateFlow()

    override fun setLastTargetName(name: String) = usecase.setLastTargetUC.execute(name)

    override fun getNamesList() {
        viewModelScope.launch(Dispatchers.IO) {
            val pinned = usecase.getPinnedListUC.execute()
            _state.value.loadingState = States.Loading
            _state.update { it.copy(data = NamesList(pinned)) }
            handleResult(
                execute = {
                    val list = usecase.getNamesList.execute().toMutableList().apply {
                        removeAll(pinned)
                    }
                    _state.update {
                        it.copy(
                            data = NamesList(_state.value.data!!.pinned, list),
                            loadingState = States.Success
                        )
                    }
                },
                onFailure = {
                    val list = usecase.getSavedNamesList.execute()?.toMutableList()?.apply {
                        removeAll(pinned)
                    }
                    _state.update {
                        it.copy(
                            data = NamesList(pinned, list ?: emptyList()),
                            loadingState = States.Error
                        )
                    }
                }
            )
        }
    }

    override fun setPinnedList(list: NamesList, item: String) {
        _state.update { it.copy(data = usecase.setPinnedListUC.execute(list, item)) }
    }

    override fun navigateToScheduleScreen(name: String, isTeacher: Boolean) {
        navigator.navigateToScheduleScreen(name, isTeacher)
    }

    override fun getLastTargetName(): String = usecase.getLastTargetUC.execute()

    override fun setHintBoxVisibility() {
        usecase.setHintStateUC.execute(false)
    }

    override fun getHintBoxVisibility(): Boolean = usecase.getHintStateUC.execute()
}