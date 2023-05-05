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

    implementation(Dependencies.koinAndroid)

    implementation(Dependencies.sqlDelightDriver)
    implementation(Dependencies.sqlDelightRuntime)
    implementation(Dependencies.sqlDelightCoroutinesExtension)
    implementation(Dependencies.kotlinSerialization)
}

sqldelight {
    database("TurtleDatabase") {
        packageName = "com.turtleteam.turtle_database"
    }
}