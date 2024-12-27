plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.turtleteam.widget_schedule"
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
    viewBinding.enable = true
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.theme))

    implementation(libs.ktor.client.okhttp)
    implementation(libs.kotlinx.serialization)
    implementation(libs.androidx.viewpager)
    implementation(libs.koin.android)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.android.core)
    implementation(libs.android.appcompat)
    implementation(libs.android.material)
}