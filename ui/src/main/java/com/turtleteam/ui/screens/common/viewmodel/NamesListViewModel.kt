package com.turtleteam.ui.screens.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.other.States
import com.turtleteam.domain.model.teachersandgroups.NamesList
import com.turtleteam.domain.usecases.GetLastTargetUC
import com.turtleteam.domain.usecases.GetListAndPinnedListUC
import com.turtleteam.domain.usecases.SetLastTargetUC
import com.turtleteam.domain.usecases.SetPinnedListUC
import com.turtleteam.ui.screens.navigation.controller.Navigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class NamesListViewModel : ViewModel() {

    abstract val state: StateFlow<States<NamesList>>

    abstract fun setLastTargetName(name: String)

    abstract fun getNamesList()

    abstract fun setPinnedList(list: NamesList, item: String)

    abstract fun navigateToScheduleScreen(name: String, isTeacher: Boolean)

    abstract fun getLastTargetName(): String
}

class NamesListUsecasesProvider(
    val getLastTargetUC: GetLastTargetUC,
    val setLastTargetUC: SetLastTargetUC,
    val setPinnedListUC: SetPinnedListUC,
    val getPinnedListUC: GetListAndPinnedListUC
)

class NamesViewModelImpl(
    private val navigator: Navigator,
    private val usecase: NamesListUsecasesProvider
) : NamesListViewModel() {

    private val _state = MutableStateFlow<States<NamesList>>(States.Loading)
    override val state: StateFlow<States<NamesList>>
        get() = _state.asStateFlow()

    override fun setLastTargetName(name: String) = usecase.setLastTargetUC.execute(name)

    override fun getNamesList() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = States.Success(usecase.getPinnedListUC.execute())
        }
    }

    override fun setPinnedList(list: NamesList, item: String) {
        _state.value = States.Success(usecase.setPinnedListUC.execute(list, item))
    }

    override fun navigateToScheduleScreen(name: String, isTeacher: Boolean) {
        navigator.navigateToScheduleScreen(name, isTeacher)
    }

    override fun getLastTargetName(): String = usecase.getLastTargetUC.execute()
}