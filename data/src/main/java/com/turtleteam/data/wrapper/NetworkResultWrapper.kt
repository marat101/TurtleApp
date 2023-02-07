package com.turtleteam.data.wrapper

import android.util.Log
import com.turtleteam.domain.model.Schedule
import com.turtleteam.domain.model.States
import com.turtleteam.domain.model.schedule.DaysList
import com.turtleteam.ktor_client.exceptions.HttpException
import java.net.UnknownHostException

object NetworkResultWrapper {

    suspend fun <T : Schedule> wrapWithResult(block: suspend () -> T?): States<DaysList> {
        return try {
            val value = block()
            if (value == null || (value is Iterable<*> && value.none())) {
                States.NotFoundError
            } else {
                States.Success(DaysList(value.days, value.name))
            }
        } catch (httpException: HttpException) {
            States.Error(httpException)
        } catch (unknownHostException: UnknownHostException) {
            States.ConnectionError
        } catch (throwable: Throwable) {
            States.Error(throwable)
        }
    }
}
