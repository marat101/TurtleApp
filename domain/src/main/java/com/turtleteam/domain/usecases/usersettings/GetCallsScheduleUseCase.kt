package com.turtleteam.domain.usecases.usersettings

import com.turtleteam.domain.repository.Assets

class GetCallsScheduleUseCase(private val assets: Assets) {

    fun execute() = assets.getCallsSchedule()
}