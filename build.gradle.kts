buildscript {

    dependencies {
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.sqldelight) apply false
    alias(libs.plugins.compose.compiler) apply false
}