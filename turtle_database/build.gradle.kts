plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

android {
    namespace = "com.turtleteam.turtle_database"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.koin.android)

    implementation(libs.sqldelight.android)
    implementation(libs.sqldelight.runtime)
    implementation(libs.sqldelight.coroutines)
    implementation(libs.kotlinx.coroutines.core)
}

sqldelight {
    database("TurtleDatabase") {
        packageName = "com.turtleteam.turtle_database"
    }
}