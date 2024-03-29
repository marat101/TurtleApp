plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(Modules.domain))
    implementation(project(Modules.remoteDatabase))
    implementation(project(Modules.theme))
//
//    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
//    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("com.github.judemanutd:autostarter:1.1.0")

    implementation(Dependencies.composeNavigationAnimation)
    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.koinCompose)
    implementation(Dependencies.androidCore)
    implementation(Dependencies.androidLifecycle)
    implementation(Dependencies.composeCompiler)
    implementation(Dependencies.composeActivity)
//    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeMaterial)
}
