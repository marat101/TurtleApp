package com.turtleteam.turtle_database

class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {

        return "Hello, ${platform.name}!"
    }
}