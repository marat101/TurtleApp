package com.turtleteam.database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform