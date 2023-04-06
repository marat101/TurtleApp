plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.turtleteam.remote_database"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Koin
    val koin = "3.2.0"
    implementation("io.insert-koin:koin-android:$koin")

    // Ktor
    val ktorVersion = "2.2.2"
    implementation("io.ktor:ktor-client-core:$ktorVersion")

    // Kotlin Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.4.5")
}