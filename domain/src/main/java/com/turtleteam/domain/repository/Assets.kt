package com.turtleteam.domain.repository

import com.turtleteam.domain.model.callschedule.Calls

interface Assets {

    fun getCallsSchedule(): ArrayList<Calls>

}