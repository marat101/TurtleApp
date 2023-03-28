buildscript {
    val sqlDelightVersion = "1.5.5"

    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.android.tools.build:gradle:7.4.2")
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
    kotlin("plugin.serialization") version "1.7.20" apply false
    id("com.squareup.sqldelight") version "1.5.5" apply false
}