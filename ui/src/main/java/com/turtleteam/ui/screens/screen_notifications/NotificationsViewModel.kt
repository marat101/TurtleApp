package com.turtleteam.ui.screens.screen_notifications

import android.provider.Settings
import androidx.lifecycle.viewModelScope
import com.turtleteam.domain.model.notifications.Notification
import com.turtleteam.domain.repository.NotificationsRepository
import com.turtleteam.domain.repository.SubscriptionsRepository
import com.turtleteam.ui.screens.common.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named

data class NotificationsScreenState(
    val notifications: List<Notification> = emptyList(),
    val fcmToken: String = "",
    val deviceId: String = ""
)

abstract class NotificationsViewModel : BaseViewModel() {

    abstract val state: StateFlow<NotificationsScreenState>
}

class NotificationsViewModelImpl(
    private val token: SubscriptionsRepository,
    private val notificationsRepository: NotificationsRepository
) : NotificationsViewModel(), KoinComponent {

    private val _state = MutableStateFlow(NotificationsScreenState())
    override val state: StateFlow<NotificationsScreenState>
        get() = _state.combine(
            flow = notificationsRepository.getNotification(),
            transform = { state, notif -> state.copy(notifications = notif.reversed()) })
            .stateIn(viewModelScope, SharingStarted.Lazily, NotificationsScreenState())

    init {
        val deviceId: String by inject(named(Settings.Secure.ANDROID_ID))
        _state.update { it.copy(fcmToken = token.getToken()?: "empty", deviceId = deviceId) }
    }
}