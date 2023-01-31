package com.turtleteam.domain.repository

import com.android.turtleapp.data.model.callschedule.CallsItem

interface Assets {

    fun getCallsSchedule():  ArrayList<CallsItem>

}