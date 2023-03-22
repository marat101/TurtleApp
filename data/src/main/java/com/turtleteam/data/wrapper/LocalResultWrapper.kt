package com.turtleteam.data.wrapper


class LocalResultWrapper {
//
//    suspend fun <T : StatefulModel> wrapWithResult(block: suspend () -> T?): States<DaysList> {
//        return try {
//            val value = block()
//            if (value == null || (value is Iterable<*> && value.none())) {
//                States.NotFoundError
//            } else {
//                States.Success(DaysList(value.days, value.name))
//            }
//        } catch (throwable: Throwable) {
//            States.Error
//        }
//    }
}