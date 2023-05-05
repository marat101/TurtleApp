buildscript {

    dependencies {
        classpath(Plugins.sqlDelight)
        classpath(Plugins.googleServices)
        classpath(Plugins.gradle)
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.android.application").version(PluginVersion.androidApp).apply(false)
    kotlin("android").version(PluginVersion.kotlin).apply(false)
    id("org.jetbrains.kotlin.jvm") version PluginVersion.kotlin apply false
    kotlin("plugin.serialization") version PluginVersion.kotlin apply false
    id("com.squareup.sqldelight") version Versions.sqlDelight apply false
}