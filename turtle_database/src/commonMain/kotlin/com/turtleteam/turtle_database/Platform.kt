package com.turtleteam.turtle_database

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform