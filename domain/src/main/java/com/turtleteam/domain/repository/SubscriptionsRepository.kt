package com.turtleteam.domain.repository

interface SubscriptionsRepository {

    //TODO

    fun saveFCMToken(token: String)

    fun getToken(): String?
}