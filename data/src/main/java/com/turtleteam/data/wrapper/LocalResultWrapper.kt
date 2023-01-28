package com.turtleteam.data.wrapper

import com.turtleteam.domain.model.Schedule
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList


class LocalResultWrapper {

    suspend fun <T : Schedule> wrapWithResult(block: suspend () -> T?): States<DaysList> {
        return try {
            val value = block()
            if (value == null || (value is Iterable<*> && value.none())) {
                States.NotFoundError
            } else {
                States.Success(DaysList(value.days, value.name))
            }
        } catch (throwable: Throwable) {
            States.Error(throwable)
        }
    }
}