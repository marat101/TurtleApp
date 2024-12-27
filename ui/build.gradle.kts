plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.turtleteam.ui"
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

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(Modules.domain))
    implementation(project(Modules.remoteDatabase))
    implementation(project(Modules.theme))

    implementation(libs.androidx.ui)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.ui.tooling.preview)

    implementation(platform(libs.compose.bom))
    implementation(libs.accompanist.navigationAnimation)
    implementation(libs.android.material)
    implementation(libs.koin.android.compose)
    implementation(libs.android.core)
    implementation(libs.android.lifecycle)
    implementation(libs.compose.activity)
    implementation(libs.compose.material)
    debugImplementation(libs.compose.preview)
}
