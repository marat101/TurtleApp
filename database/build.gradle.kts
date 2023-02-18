plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

android {
    namespace = "com.turtleteam.database"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
dependencies {
    val koin = "3.2.0"
    implementation("io.insert-koin:koin-core:$koin")

    val sqlDelightVersion = "1.5.5"
    implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
    implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

    //kotlinx
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

sqldelight {
    database("TurtleDatabase") {
        packageName = "com.turtleteam.database.sqldelight"
//        sourceFolders = listOf("sqldelight")
    }
}