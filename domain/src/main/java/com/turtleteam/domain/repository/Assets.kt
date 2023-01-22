package com.turtleteam.domain.repository

import com.android.turtleapp.data.model.callschedule.Calls

interface Assets {

    fun getCallsSchedule(): Calls

}