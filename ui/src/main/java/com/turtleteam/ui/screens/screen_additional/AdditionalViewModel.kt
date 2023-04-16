package com.turtleteam.ui.screens.screen_additional

import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.callschedule.Calls
import com.turtleteam.domain.usecases_impl.usersettings.GetCallsScheduleUseCase
import com.turtleteam.remote_database.AppUpdate
import com.turtleteam.remote_database.Update
import com.turtleteam.ui.screens.common.viewmodel.base.BaseViewModel
import com.turtleteam.ui.screens.navigation.controller.Navigator
import com.turtleteam.ui.utils.Constants
import com.turtleteam.ui.utils.PagerListener
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AdditionalState(
    val googleSheetUrl: String = "https://drive.google.com/drive/folders/1UH5pcJc0pxkYFWBMI_bNCxrdlApqv3nX",
    val calls: List<Calls> = emptyList(),
    val isExpandedCalls: Boolean = false,
    val updateState: AppUpdate = AppUpdate.Empty
)

abstract class AdditionalViewModel : BaseViewModel() {
    abstract val state: StateFlow<AdditionalState>

    abstract fun clickOnCalls()

    abstract fun navigateToGoogleSheet()

    abstract fun navigateToTurtleTeamVK()

    abstract fun initPageListener()

    abstract fun clickOnUpdate()
}

class AdditionalViewModelImpl(
    private val page: Int = 0,
    private val pageListener: PagerListener,
    private val usecase: GetCallsScheduleUseCase,
    private val navigator: Navigator,
    private val update: Update
) : AdditionalViewModel() {

    private val _state = MutableStateFlow(AdditionalState())
    override val state: StateFlow<AdditionalState>
        get() = _state.combine(update.state) { state, update -> state.copy(updateState = update) }
            .stateIn(viewModelScope, SharingStarted.Lazily, _state.value)

    init {
        viewModelScope.launch {
            _state.update { it.copy(calls = usecase.execute()) }
        }
    }

    override fun clickOnCalls() {
        _state.update { it.copy(isExpandedCalls = !it.isExpandedCalls) }
    }

    override fun navigateToGoogleSheet() {
        navigator.openLink(state.value.googleSheetUrl)
    }

    override fun navigateToTurtleTeamVK() {
        navigator.openLink(Constants.TURTLE_TEAM_LINK)
    }

    override fun initPageListener() {
        viewModelScope.launch {
            pageListener.getPageListener(page).collectLatest { isCurrent ->
                if (!isCurrent)
                    _state.update { it.copy(isExpandedCalls = false) }
            }
        }
    }

    override fun clickOnUpdate() {
        if (update.state.replayCache.last() is AppUpdate.Success)
            navigator.openLink((update.state.replayCache.last() as AppUpdate.Success).link)
    }
}