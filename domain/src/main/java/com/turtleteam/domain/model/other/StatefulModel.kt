package com.turtleteam.domain.model.other

data class StatefulModel<T>(
    var data: T? = null,
    var loadingState: States = States.Loading
)