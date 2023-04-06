package com.turtleteam.ui.screens.screen_additional

import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.callschedule.Calls
import com.turtleteam.domain.usecases_impl.usersettings.GetCallsScheduleUseCase
import com.turtleteam.ui.screens.common.viewmodel.base.BaseViewModel
import com.turtleteam.ui.screens.navigation.controller.Navigator
import com.turtleteam.ui.utils.Constants
import com.turtleteam.ui.utils.PagerListener
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AdditionalState(
    //TODO получение ссылки из файрбейз
    val googleSheetUrl: String = "https://drive.google.com/drive/folders/1UH5pcJc0pxkYFWBMI_bNCxrdlApqv3nX",
    val calls: List<Calls> = emptyList(),
    val isExpandedCalls: Boolean = false,
    val isUpdateVisible: Boolean = false
)

abstract class AdditionalViewModel : BaseViewModel() {
    abstract val state: StateFlow<AdditionalState>

    abstract fun clickOnCalls()

    abstract fun navigateToGoogleSheet()

    abstract fun navigateToTurtleTeamVK()

    abstract fun clickOnupdate() ///TODO
}

class AdditionalViewModelImpl(
    page: Int = 0,
    private val pageListener: PagerListener,
    private val usecase: GetCallsScheduleUseCase,
    private val navigator: Navigator,
) : AdditionalViewModel() {

    private val _state = MutableStateFlow(AdditionalState())
    override val state: StateFlow<AdditionalState>
        get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(calls = usecase.execute()) }
            pageListener.getPageListener(page).collectLatest {
                if (!it)
                    _state.update { it.copy(isExpandedCalls = false) }
            }
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

    override fun clickOnupdate() {
        TODO("Not yet implemented")
    }
}