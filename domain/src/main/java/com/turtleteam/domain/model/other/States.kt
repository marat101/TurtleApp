package com.turtleteam.domain.model.other

sealed interface States {

    object Success : States

    object Error : States

    object NotFoundError : States

    object Loading : States
}