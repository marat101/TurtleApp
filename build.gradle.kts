buildscript {

    dependencies {
        classpath(Plugins.sqlDelight)
        classpath(Plugins.googleServices)
        classpath(Plugins.gradle)
        classpath("com.android.tools.build:gradle:3.4.0")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
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