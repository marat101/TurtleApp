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
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)

    implementation(libs.koin.android)
    implementation(libs.ktor.client.core)
    implementation(libs.kotlinx.serialization)
    implementation(libs.android.core)
    implementation(libs.android.appcompat)
}