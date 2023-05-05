plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.turtleteam.remote_database"
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
    implementation("com.google.firebase:firebase-firestore-ktx:24.6.0")

    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.ktorCore)
    implementation(Dependencies.kotlinSerialization)
    implementation(Dependencies.androidCore)
    implementation(Dependencies.androidAppCompat)
}